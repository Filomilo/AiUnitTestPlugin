package UnitTestGenerator.LLM.Containers.Docker

import UnitTestGenerator.LLM.Containers.Config.ContainerConfiguration
import UnitTestGenerator.LLM.Containers.Config.ContainerStatus
import UnitTestGenerator.LLM.Containers.ContainersManager
import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.command.*
import com.github.dockerjava.api.exception.ConflictException
import com.github.dockerjava.api.exception.NotFoundException
import com.github.dockerjava.api.model.*
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

object DockerConnection : ContainersManager {
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


    override fun getRunningContainersList(): List<String> {
        val conatinerList = dockerClient.listContainersCmd().exec()
        val containersId: List<String> = contianerListToIdList(conatinerList)
        return containersId
    }

    override fun getContainersList(): List<String> {
        val conatinerList = dockerClient.listContainersCmd().withShowAll(true).exec()
        val containersId: List<String> = contianerListToIdList(conatinerList)

        return containersId
    }

    override fun close() {
        dockerClient.close()
    }


    fun contianerListToIdList(contiaenrList: List<Container>): List<String> {
        return contiaenrList.stream().map { obj: Container -> obj.id }.toList()
    }

    @Throws(InterruptedException::class)
    override fun pullImageSync(image: String?) {
        val pullImageCmd = dockerClient.pullImageCmd(image!!)
        val callback = pullImageCmd.exec(PullImageResultCallback())
        callback.awaitCompletion()
    }

    override fun createContainer(containerConfiguration: ContainerConfiguration): String {
//        TODO("not imlmetneds")
        val hostConfig = HostConfig.newHostConfig().withMemory(containerConfiguration.ramBytes)
        if (containerConfiguration.portConfiguration.isNotEmpty()) {
            val portBindings = containerConfiguration.portConfiguration.map { x ->
                PortBinding(
                    Ports.Binding("0.0.0.0", x.exposedPort.toString()),
                    ExposedPort(x.internalPort)
                )
            }

            hostConfig
                .withPortBindings(portBindings)
        }
        if (containerConfiguration.mountVolumes.isNotEmpty()) {
            hostConfig.withMounts(
                containerConfiguration.mountVolumes.map { x ->
                    Mount().withType(MountType.VOLUME)
                        .withSource(x.name)
                        .withTarget(x.interalPath)
                }.toList()
            )
        }

        var response: CreateContainerResponse
        var DockerContainerCmd: CreateContainerCmd =
            dockerClient.createContainerCmd(containerConfiguration.image).withHostConfig(hostConfig);
        if (containerConfiguration.portConfiguration.isNotEmpty()) {
            DockerContainerCmd.withExposedPorts(
                (containerConfiguration.portConfiguration.map { x -> ExposedPort(x.exposedPort) }.toList())
            )
        }
        try {
            response = DockerContainerCmd.exec()
        } catch (ex: NotFoundException) {
            log.warn("Docker image: ${containerConfiguration.image} Image locally attempting to pull it from repository")
            try {
                pullImageSync(containerConfiguration.image)



                response = DockerContainerCmd
                    .exec()
            } catch (e: Exception) {
                throw Exception("Couldn't create container: " + e.message)
            }
        }
        val virtualMachineIdentifiaction = response.id

        return virtualMachineIdentifiaction

    }

    override fun destroyContainer(containerid: String) {
        log.info("destroying vm with id: $containerid")
        try {
            dockerClient.removeContainerCmd(containerid).exec()
        } catch (ex: ConflictException) {
            log.warn("warning, container $containerid id still running but will be exited to destroy it")
            stopContainer(containerid)
            dockerClient.removeContainerCmd(containerid).exec()
        }
    }

    override fun stopContainer(id: String) {
        log.info("stopping container $id")
        dockerClient.stopContainerCmd(id).exec()
    }

    override fun startContainer(id: String) {
        dockerClient.startContainerCmd(id).exec()
    }

    fun getContianerFromID(id: String): Container? {
        return try {
            dockerClient
                .listContainersCmd()
                .withShowAll(true)
                .withFilter("id", ArrayList(listOf(id)))
                .exec()[0]
        } catch (ex: IndexOutOfBoundsException) {
            null
        }
    }

    override fun getStatus(id: String): ContainerStatus {
        try {
            val status: String = getContianerFromID(id)!!.getStatus()
            log.info("gettign status: $status")
            if (status.contains("Up")) return ContainerStatus.RUNNING_MACHINE
            if (status.contains("Exited")) return ContainerStatus.MACHINE_STOPPED
        } catch (ex: NullPointerException) {
            return ContainerStatus.DESTROYED
        }
        return ContainerStatus.DESTROYED
    }

    override fun getOpenPort(id: String): Int {
        return getContianerFromID(id)!!.getPorts()[0].publicPort!!;
    }
}