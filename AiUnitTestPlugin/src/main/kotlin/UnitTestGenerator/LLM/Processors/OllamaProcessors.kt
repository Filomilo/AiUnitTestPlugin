package UnitTestGenerator.LLM.Processors

import UnitTestGenerator.LLM.Apis.Ollama.*
import UnitTestGenerator.LLM.Containers.Docker.DockerConnection
import UnitTestGenerator.LLM.LLMProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.log

class OllamaProcessors(model: String, ollamaApi: OllamaApi) : LLMProcessor {
    private val log: Logger = LoggerFactory.getLogger(DockerConnection::class.java)

    val ollamaApi: OllamaApi;
    val model: String;

    init {
        this.ollamaApi = ollamaApi
        this.model = model
    }

    override fun executePrompt(prompt: String): String {
        val ollamaGenerateResponse: OllamaGenerateResponse = this.ollamaApi.generate(
            OllamaGenerateRequest(
                prompt = prompt,
                model = this.model,
                stream = false
            )
        )
        return ollamaGenerateResponse.response;
    }

    override fun load() {
        log.info("Ollama pulling ${this.model}")
        this.ollamaApi.pull(
            OllamaPullRequest(
                model = this.model,
            )
        )

    }

    override fun unload() {
        this.ollamaApi.delete(OllamaDeleteRequest(model = this.model))
    }
}