package org.filomilo.AiTestGenerator.LLM

interface LLMProcessor {
    fun executePrompt(prompt: String): String
    fun load()
    fun unload()
}