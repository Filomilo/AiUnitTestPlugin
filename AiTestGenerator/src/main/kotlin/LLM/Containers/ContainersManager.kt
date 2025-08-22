package org.filomilo.AiTestGenerator.LLM.Containers

import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerConfiguration
import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerStatus


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