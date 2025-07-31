package UnitTestGenerator.LLM.Containers

import UnitTestGenerator.LLM.Containers.Config.ContainerConfiguration
import UnitTestGenerator.LLM.Containers.Config.ContainerStatus

open class UniversalContainer(ContainersManager: ContainersManager, ContainerConfiguration: ContainerConfiguration) :
    Container {
    val ContainersManager: ContainersManager = ContainersManager
    val id: String

    init {
        id = ContainersManager.createContainer(ContainerConfiguration)
    }


    override fun start() {
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

}