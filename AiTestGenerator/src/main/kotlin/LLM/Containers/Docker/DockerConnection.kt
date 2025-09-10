package org.filomilo.AiTestGenerator.LLM.Containers.Docker


import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.async.ResultCallbackTemplate
import com.github.dockerjava.api.command.*
import com.github.dockerjava.api.exception.ConflictException
import com.github.dockerjava.api.exception.NotFoundException
import com.github.dockerjava.api.model.*
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerConfiguration
import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerStatus
import org.filomilo.AiTestGenerator.LLM.Containers.ContainersManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import java.time.Duration

object DockerConnection : ContainersManager {
    private val log: Logger = LoggerFactory.getLogger(DockerConnection::class.java)


    private val dockerClient: DockerClient;
    private var networkId: String;

    init {
        val dockerHost = System.getenv("DOCKER_HOST")
        log.info("----------------------------------------------------------------dockerHost: $dockerHost")
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
//        try {
//            val CreateContainerResponse: CreateNetworkResponse =
//                this.dockerClient.createNetworkCmd()
//                    .withDriver("bridge")
//                    .withName("all").exec();
//            this.networkId = CreateContainerResponse.id;
//        } catch (Ex: Exception) {
//            log.info(Ex.message)
//            val networks = dockerClient.listNetworksCmd()
//                .withFilter("name", listOf("all"))
//
//                .exec()
        val networkName = System.getenv("DOCKER_NETWORK")

        val networkResponseID: String = dockerClient.listNetworksCmd()
            .withNameFilter(networkName)
            .exec()
            .firstOrNull()?.id ?: dockerClient.createNetworkCmd()
            .withName(networkName)
            .withDriver("bridge")
            .exec().id
        this.networkId = networkResponseID
//        }
        log.info("Network ID: $networkId")

    }


    override fun getRunningContainersList(): List<String> {
        val conatinerList = dockerClient.listContainersCmd().exec()
        val containersId: List<String> = contianerListToIdList(conatinerList)
        return containersId
    }

    override fun getContainersList(): List<String> {
        val conatinerList = dockerClient.listContainersCmd().withShowAll(true).exec()
        val containersId: List<String> = contianerListToIdList(conatinerList)
        log.info("Get conatienr list: \n\n${containersId.stream().toArray().joinToString(", \n")}")
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
        log.info("Creating Docker container with onfiguration :\n ${containerConfiguration}")
        val hostConfig = HostConfig.newHostConfig()

        val networkName = System.getenv("DOCKER_NETWORK")
        if (networkName !== null) {
            log.info("Creating Docker container with network name $networkName")
            hostConfig.withNetworkMode(networkName)
        }

        if (containerConfiguration.ramBytes != null) {
            hostConfig.withMemory(containerConfiguration.ramBytes)
        }


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
//        DockerContainerCmd.withNetworkDisabled(false)

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
        log.info(
            "Created Docker container with id :\n ${virtualMachineIdentifiaction} and has status: ${
                dockerClient.inspectContainerCmd(virtualMachineIdentifiaction).exec().state
            }\""
        )

//        dockerClient.connectToNetworkCmd().withContainerId(virtualMachineIdentifiaction)
//            .withNetworkId(this.networkId).exec();
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
        log.info("starting Docker container with id :\n ${id}")

        dockerClient.startContainerCmd(id).exec()

        log.info(
            "started Docker container with id :\n ${id} : and has status: ${
                dockerClient.inspectContainerCmd(id).exec().state
            }"
        )

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


    override fun getLogs(id: String): String {
        val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()


        dockerClient.logContainerCmd(id)
            .withStdOut(true)
            .withStdErr(true)
            .withFollowStream(false) // don't keep streaming forever
            .withSince(0)            // get logs from container start
            .exec(object : ResultCallbackTemplate<Nothing?, Frame>() {
                override fun onNext(frame: Frame) {
                    outputStream.writeBytes(frame.payload)
                }
            })
            .awaitCompletion()
        val outputString: String = outputStream.toString(StandardCharsets.UTF_8)

        return outputString

    }
}
