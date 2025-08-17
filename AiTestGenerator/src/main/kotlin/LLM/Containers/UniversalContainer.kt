package org.filomilo.AiTestGenerator.LLM.Containers


import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerConfiguration
import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class UniversalContainer(ContainersManager: ContainersManager, ContainerConfiguration: ContainerConfiguration) :
    Container {
    val ContainersManager: ContainersManager = ContainersManager
    val id: String
    private val log: Logger = LoggerFactory.getLogger(UniversalContainer::class.java)

    init {
        id = ContainersManager.createContainer(ContainerConfiguration)
    }


    override fun start() {
        log.info("${id} start")
        ContainersManager.startContainer(id)
    }

    override fun stop() {
        ContainersManager.stopContainer(id)
    }

    override fun destroy() {
        ContainersManager.destroyContainer(id)
    }

    override fun getStatus(): ContainerStatus {
        return ContainersManager.getStatus(id);
    }

    override fun getOpenPort(): Int {
        return ContainersManager.getOpenPort(id);
    }

    override fun getLogs(): String {
        return ContainersManager.getLogs(id);
    }

}