package org.filomilo.AiTestGenerator.LLM.Processors


import DeviceSpecification
import org.filomilo.AiTestGenerator.LLM.Apis.Ollama.*
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.time.Duration
import kotlin.time.measureTime

class OllamaProcessors(model: String, ollamaApi: OllamaApi) : LLMProcessor {
    private val log: Logger = LoggerFactory.getLogger(DockerConnection::class.java)

    val ollamaApi: OllamaApi;
    val model: String;

    init {
        this.ollamaApi = ollamaApi
        this.model = model

    }

    override fun executePrompt(prompt: String, jsonFormat: String?): LLMResponse {

        var ollamaGenerateResponse: OllamaGenerateResponse?
        val duration: Duration = measureTime {
            ollamaGenerateResponse = this.ollamaApi.generate(
                OllamaGenerateRequest(
                    prompt = prompt,
                    model = this.model,
                    stream = false,
                    format= jsonFormat

                )
            )
        }


        return LLMResponse(
            prompt = prompt,
            response = ollamaGenerateResponse!!.response,
            generationTime = duration,
            deviceSpecification = this.getDeviceSpecification(),
            modelName = this.getName(),
            jsonFormat = jsonFormat
        );
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
//        this.ollamaApi.delete(OllamaDeleteRequest(model = this.model))
    }

    override fun getName(): String {
        return "Ollama-${this.model}"
    }

    override fun getDeviceSpecification(): DeviceSpecification? {
        return DeviceSpecification.getCurrentDeviceSpecification()
    }



    override fun toString(): String {
        return "OllamaProcessors(model='$model', ollamaApi=$ollamaApi)"
    }


}