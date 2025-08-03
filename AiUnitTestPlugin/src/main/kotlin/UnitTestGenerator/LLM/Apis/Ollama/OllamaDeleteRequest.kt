package UnitTestGenerator.LLM.Apis.Ollama

import kotlinx.serialization.Serializable

@Serializable
data class OllamaDeleteRequest(
    val model: String
) {
}