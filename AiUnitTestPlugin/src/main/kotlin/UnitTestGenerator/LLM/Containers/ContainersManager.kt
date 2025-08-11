package UnitTestGenerator.LLM.Containers

import UnitTestGenerator.LLM.Containers.Config.ContainerConfiguration
import UnitTestGenerator.LLM.Containers.Config.ContainerStatus
import java.io.Console

interface ContainersManager {
    fun getRunningContainersList(): List<String>
    fun getContainersList(): List<String>
    fun close()

    @Throws(InterruptedException::class)
    fun pullImageSync(image: String?)
    fun createContainer(containerConfiguration: ContainerConfiguration): String
    fun destroyContainer(containerid: String)
    fun stopContainer(id: String)
    fun startContainer(id: String)
    fun getStatus(id: String): ContainerStatus
    fun getOpenPort(id: String): Int
    fun getLogs(id: String): String

}