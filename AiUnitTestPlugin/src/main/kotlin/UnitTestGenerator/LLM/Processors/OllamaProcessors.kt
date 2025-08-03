package UnitTestGenerator.LLM.Processors

import UnitTestGenerator.LLM.Apis.Ollama.*
import UnitTestGenerator.LLM.LLMProcessor

class OllamaProcessors(model: String, ollamaApi: OllamaApi) : LLMProcessor {

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