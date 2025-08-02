package UnitTestGenerator.LLM.Apis.Ollama

import kotlinx.serialization.Serializable

@Serializable
data class OllamaPullResponse(
    val status: String
)
