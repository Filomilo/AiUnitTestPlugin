package UnitTestGenerator.LLM.Apis.Ollama

import UnitTestGenerator.LLM.Containers.Container
import UnitTestGenerator.LLM.Containers.Docker.DockerConnection
import UnitTestGenerator.LLM.Containers.LLMContainers.OllamaContainer

import java.util.UUID
import kotlin.random.Random

object OllamaApiGenerator {

    var containers: List<Container> = ArrayList<Container>()

    fun cleanup() {
        if (this.containers.isNotEmpty()) {
            this.containers.forEach { x: Container -> x.destroy() }
        }
    }


    fun getOllamaApi(): OllamaApi {

        var url: String = System.getenv("OLLAMA_API") ?: ""
        if (url.isEmpty()) {
            val name: String = "oolama_" + UUID.randomUUID().toString()
            var ollamaPort = Random.nextInt(10000, 20000)
            val OllamaContainer: OllamaContainer =
                OllamaContainer(containerManager = DockerConnection, name = "ollama_${name}", port = ollamaPort)
            this.containers += (OllamaContainer)
            OllamaContainer.start()
            if (System.getProperty("os.name").lowercase().contains("win")) {
                url = "http://localhost:${ollamaPort}/"
            } else {
                url = "http://${name}:11434/"
            }

        }
        val ollamaApi: OllamaApi = OllamaApi(url);
        ollamaApi.ensureActive()
        return ollamaApi;
    }


}