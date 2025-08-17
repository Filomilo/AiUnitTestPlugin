package org.filomilo.AiTestGenerator.LLM.Apis.Ollama

class NotExisitingModel(ModelName: String) : Exception("Ollama doesnt't have model $ModelName") {
}