package org.filomilo.AiTestGenerator.LLM


import org.filomilo.AiTestGenerator.LLM.Apis.ApiConnection
import org.filomilo.AiTestGenerator.LLM.Apis.Ollama.OllamaApi
import org.filomilo.AiTestGenerator.LLM.Containers.ContainersManager
import org.filomilo.AiTestGenerator.LLM.Processors.OllamaProcessors


class LlmRepository(containerManger: ContainersManager, ApiConnection: ApiConnection, OllamaApi: OllamaApi? = null) {
    var ListOfLlmProcessors: List<LLMProcessor> = ArrayList<LLMProcessor>()
    val containerManger: ContainersManager
    val apiConnection: ApiConnection

    val ollamaApi: OllamaApi?;

    init {
        this.containerManger = containerManger
        this.apiConnection = ApiConnection
        this.ollamaApi = OllamaApi
    }

    fun initlize() {


        this.ollamaApi!!.ensureActive()
        val Ollamamodels: Array<String> =
            arrayOf(
                "gemma3:1b",
                "gemma3",
////                "gemma3:12b", // Not enough memory
////                "gemma3:27b",  // Not enough memory
////                "qwq",  // Not enough memory
//                "deepseek-r1",
//                "deepseek-r1:671b", // Not enough memory
//                "llama4:scout", // Not enough memory
//                "llama4:maverick", // Not enough memory
//                "llama3.3", // Not enough memory
//                "llama3.2",
//                "llama3.2:1b",
////                "llama3.2-vision", // Not enough memory
////                "llama3.2-vision:90b",// Not enough memory
//                "llama3.1",
////                "llama3.1:405b",// Not enough memory
//                "phi4",
//                "phi4-mini",
//                "mistral",
//                "moondream",
//                "neural-chat",
//                "starling-lm",
//                "codellama",
//                "llama2-uncensored",
//                "llava",
//                "granite3.3"
            )


        Ollamamodels.forEach { x ->
            this.ListOfLlmProcessors += (OllamaProcessors(x, this.ollamaApi!!))
        }
        if (Environment.shouldCacheLLM()) {
            this.ListOfLlmProcessors = this.ListOfLlmProcessors.map { x -> CachedLLMProcessor(x) }
        }


    }
}