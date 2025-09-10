package LLM.Apis.Ollama

import org.filomilo.AiTestGenerator.LLM.Apis.Ollama.OllamaApi
import org.filomilo.AiTestGenerator.LLM.Containers.Container
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.LLM.Containers.LLMContainers.OllamaContainer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.UUID
import kotlin.random.Random

object OllamaApiGenerator {
    private val log: Logger = LoggerFactory.getLogger(OllamaApiGenerator::class.java)

    var containers: List<Container> = ArrayList<Container>()

    fun cleanup() {
        if (containers.isNotEmpty()) {
            containers.forEach { x: Container -> x.destroy() }
        }
    }


    fun getOllamaApi(): OllamaApi {
        log.info("getOllamaApi")
        var url: String = System.getenv("OLLAMA_API") ?: ""
        if (url.isEmpty()) {
            log.info("-------------------OLLAMA_API empty creating ollama container")
            val name: String = "oolama_" + UUID.randomUUID().toString()
            var ollamaPort = Random.nextInt(10000, 20000)
            val OllamaContainer: OllamaContainer =
                OllamaContainer(containerManager = DockerConnection, name = "ollama_${name}", port = ollamaPort)
            containers += (OllamaContainer)
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