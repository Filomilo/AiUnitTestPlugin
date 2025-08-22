package org.filomilo.AiTestGenerator.LLM.Containers

import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerStatus


interface Container {
    fun start()
    fun stop()
    fun destroy()
    fun getStatus(): ContainerStatus
    fun getOpenPort(): Int
    fun getLogs(): String

}