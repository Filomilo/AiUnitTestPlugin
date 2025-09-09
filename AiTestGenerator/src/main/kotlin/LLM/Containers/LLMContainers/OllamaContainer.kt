package org.filomilo.AiTestGenerator.LLM.Containers.LLMContainers

import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerConfiguration
import org.filomilo.AiTestGenerator.LLM.Containers.Config.ExposedPort
import org.filomilo.AiTestGenerator.LLM.Containers.Config.MountVolume
import org.filomilo.AiTestGenerator.LLM.Containers.ContainersManager
import org.filomilo.AiTestGenerator.LLM.Containers.UniversalContainer

class OllamaContainer(
    containerManager: ContainersManager,
    port: Int = 11434,
    ramBytes: Long = 1024 * 1024 * 1024 * 4,
    name: String = "ollama"
) : UniversalContainer(
    containerManager,
    ContainerConfiguration(
        image = "ollama/ollama",
        ramBytes = ramBytes,
        portConfiguration = arrayListOf(ExposedPort(11434, port)),
        mountVolumes = arrayListOf(MountVolume("ollama", "/root/.ollama"))
    )
) {


}
