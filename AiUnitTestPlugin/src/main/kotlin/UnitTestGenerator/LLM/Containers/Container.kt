package UnitTestGenerator.LLM.Containers

import UnitTestGenerator.LLM.Containers.Config.ContainerStatus

interface Container {
    fun start()
    fun stop()
    fun destroy()
    fun getStatus(): ContainerStatus
    fun getOpenPort(): Int


}