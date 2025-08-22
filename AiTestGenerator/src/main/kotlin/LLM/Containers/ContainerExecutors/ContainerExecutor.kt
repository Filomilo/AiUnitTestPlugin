package org.filomilo.AiTestGenerator.LLM.Containers.ContainerExecutors

import org.filomilo.AiTestGenerator.LLM.Containers.Container


interface ContainerExecutor {
    fun execute(container: Container, any: Any?)
}