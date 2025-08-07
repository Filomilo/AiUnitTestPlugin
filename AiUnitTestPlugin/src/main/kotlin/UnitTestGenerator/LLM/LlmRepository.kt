package UnitTestGenerator.LLM

import UnitTestGenerator.LLM.Apis.ApiConnection
import UnitTestGenerator.LLM.Apis.Ollama.OllamaApi
import UnitTestGenerator.LLM.Containers.ContainersManager
import UnitTestGenerator.LLM.Containers.LLMContainers.OllamaContainer
import UnitTestGenerator.LLM.Processors.OllamaProcessors
import kotlin.random.Random


class LlmRepository(containerManger: ContainersManager, ApiConnection: ApiConnection) {
    var ListOfLlmProcessors: List<LLMProcessor> = ArrayList<LLMProcessor>()
    val containerManger: ContainersManager
    val apiConnection: ApiConnection

    init {
        this.containerManger = containerManger
        this.apiConnection = ApiConnection
    }

    fun initlize() {
        val port: Int = Random.nextInt(10000, 20000)
        val OllamaContainer: OllamaContainer = OllamaContainer(containerManger, port)
        OllamaContainer.start()
        val OllamaApi: OllamaApi = OllamaApi("http://localhost:$port/")
        OllamaApi.ensureActive()
        val Ollamamodels: Array<String> =
            arrayOf(
                "gemma3:1b",
                "gemma3",
//                "gemma3:12b", // Not enough memory
//                "gemma3:27b",  // Not enough memory
//                "qwq",  // Not enough memory
//                "deepseek-r1",
//                "deepseek-r1:671b", // Not enough memory
//                "llama4:scout", // Not enough memory
//                "llama4:maverick", // Not enough memory
//                "llama3.3", // Not enough memory
//                "llama3.2",
//                "llama3.2:1b",
//                "llama3.2-vision", // Not enough memory
//                "llama3.2-vision:90b",// Not enough memory
//                "llama3.1",
//                "llama3.1:405b",// Not enough memory
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
            this.ListOfLlmProcessors += (OllamaProcessors(x, OllamaApi))
        }

    }
}