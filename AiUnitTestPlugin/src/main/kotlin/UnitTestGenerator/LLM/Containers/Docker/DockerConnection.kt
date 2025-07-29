package UnitTestGenerator.LLM.Containers.Docker

import UnitTestGenerator.LLM.Containers.ContainerConfiguration
import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.command.*
import com.github.dockerjava.api.exception.ConflictException
import com.github.dockerjava.api.exception.NotFoundException
import com.github.dockerjava.api.model.Container
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

object DockerConnection {
    private val log: Logger = LoggerFactory.getLogger(DockerConnection::class.java)


    private val dockerClient: DockerClient;


    init {
        val dockerHost = System.getenv("DOCKER_HOST")
        require(dockerHost != null) { "The docker host must be set" }

        val dockerConfiguration: DockerClientConfig =
            DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerHost)
                .withDockerTlsVerify(false)
                .withApiVersion("1.43")
                .build()

        val httpClient: DockerHttpClient =
            ApacheDockerHttpClient.Builder()
                .dockerHost(dockerConfiguration.getDockerHost())
                .sslConfig(dockerConfiguration.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build()

        this.dockerClient = DockerClientImpl.getInstance(dockerConfiguration, httpClient)
    }


    public fun getRunningVmList(): List<String> {
        val conatinerList = dockerClient.listContainersCmd().exec()
        val containersId: List<String> = contianerListToIdList(conatinerList)
        return containersId
    }

    public fun getVmList(): List<String> {
        val conatinerList = dockerClient.listContainersCmd().withShowAll(true).exec()
        val containersId: List<String> = contianerListToIdList(conatinerList)

        return containersId
    }

    public fun close() {
        dockerClient.close()
    }


    fun contianerListToIdList(contiaenrList: List<Container>): List<String> {
        return contiaenrList.stream().map { obj: Container -> obj.id }.toList()
    }

    @Throws(InterruptedException::class)
    fun pullImageSync(image: String?) {
        val pullImageCmd = dockerClient.pullImageCmd(image!!)
        val callback = pullImageCmd.exec(PullImageResultCallback())
        callback.awaitCompletion()
    }

    fun createContainer(containerConfiguration: ContainerConfiguration): String {
//        TODO("not imlmetneds")
        val hostConfig = HostConfig.newHostConfig().withMemory(containerConfiguration.ramBytes)

        var response: CreateContainerResponse
        try {
            response = dockerClient.createContainerCmd(containerConfiguration.image).withHostConfig(hostConfig).exec()
        } catch (ex: NotFoundException) {
            log.warn("Docker image: ${containerConfiguration.image} Image locally attempting to pull it from repository")
            try {
                pullImageSync(containerConfiguration.image)
                response =
                    dockerClient.createContainerCmd(containerConfiguration.image).withHostConfig(hostConfig).exec()
            } catch (e: Exception) {
                throw Exception("Couldn't create container: " + e.message)
            }
        }
        val virtualMachineIdentifiaction = response.id

        return virtualMachineIdentifiaction

    }

    fun destroyContainer(containerid: String) {
        log.info("destroying vm with id: $containerid")
        try {
            dockerClient.removeContainerCmd(containerid).exec()
        } catch (ex: ConflictException) {
            log.warn("warning, container $containerid id still running but will be exited to destroy it")
            stopContainer(containerid)
            dockerClient.removeContainerCmd(containerid).exec()
        }
    }

    fun stopContainer(id: String) {
        log.info("stopping container $id")
        dockerClient.stopContainerCmd(id).exec()
    }
}