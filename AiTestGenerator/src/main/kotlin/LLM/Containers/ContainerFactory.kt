package org.filomilo.AiTestGenerator.LLM.Containers

object ContainerFactory {
    enum class ContainerType {
        DOCKER, PODMAN
    }

    fun CreateContainer(type: ContainerType): Container {
        return when (type) {
            ContainerType.DOCKER -> TODO("Unimplemented property container type")
            ContainerType.PODMAN -> TODO("Unimplemented property container type")
//            else -> TODO("Unimplemented property container type")
        }
    }


}