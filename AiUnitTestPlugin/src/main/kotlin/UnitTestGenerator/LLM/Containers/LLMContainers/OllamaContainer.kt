package UnitTestGenerator.LLM.Containers.LLMContainers

import UnitTestGenerator.LLM.Containers.*
import UnitTestGenerator.LLM.Containers.Config.ContainerConfiguration
import UnitTestGenerator.LLM.Containers.Config.ExposedPort

class OllamaContainer(
    containerManager: ContainersManager,
    port: Int = 11434,
    ramBytes: Long = 1024 * 1024 * 512
) : UniversalContainer(
    containerManager,
    ContainerConfiguration(
        image = "ollama/ollama",
        ramBytes = ramBytes,
        portConfiguration = arrayListOf(ExposedPort(11434, port))
    )
) {


}
