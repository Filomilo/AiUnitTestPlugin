package org.filomilo.AiTestGenerator.LLM.Apis.Ollama

import kotlinx.serialization.Serializable

@Serializable
data class OllamaVersionResponse(val version: String)
