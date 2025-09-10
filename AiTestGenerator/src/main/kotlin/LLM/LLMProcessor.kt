package org.filomilo.AiTestGenerator.LLM

import DeviceSpecification

interface LLMProcessor {
    fun executePrompt(prompt: String): String
    fun load()
    fun unload()
    fun getName(): String
    fun getDeviceSpecification(): DeviceSpecification?
}