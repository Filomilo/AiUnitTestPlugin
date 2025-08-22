package org.filomilo.AiTestGenerator.LLM.Containers.Docker

import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerConfiguration
import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerStatus
import org.filomilo.AiTestGenerator.LLM.Containers.Container


class ContainerDocker : Container {

    lateinit var id: String

    constructor(containerConfiguration: ContainerConfiguration) {
        this.id = DockerConnection.createContainer(containerConfiguration)
    }

    override fun start() {
        DockerConnection.startContainer(this.id)
    }

    override fun stop() {
        DockerConnection.stopContainer(this.id)
    }

    override fun destroy() {
        DockerConnection.destroyContainer(this.id)
    }

    override fun getStatus(): ContainerStatus {
        TODO("Not yet implemented")
    }

    override fun getOpenPort(): Int {
        return DockerConnection.getOpenPort(this.id)
    }

    override fun getLogs(): String {
        return DockerConnection.getLogs(this.id)
    }


}