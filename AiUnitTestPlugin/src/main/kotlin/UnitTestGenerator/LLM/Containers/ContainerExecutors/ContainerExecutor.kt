package UnitTestGenerator.LLM.Containers.ContainerExecutors

import UnitTestGenerator.LLM.Containers.Container

interface ContainerExecutor {
    fun execute(container: Container, any: Any?)
}