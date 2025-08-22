package org.filomilo.AiTestGenerator.LLM.Apis.Ollama

import kotlinx.serialization.json.Json
import org.filomilo.AiTestGenerator.LLM.Apis.ApiConnectionFactory
import org.filomilo.AiTestGenerator.Tools.Awaiters
import java.time.Duration

class OllamaApi(urlBase: String) {

    var urlBase: String;

    init {
        this.urlBase = urlBase
    }

    private fun resolveException(body: String, modelReq: String? = null) {
        if (body.contains("{\"error\":\"model 'NonExisitng' not found\"}")) {
            throw NotExisitingModel(modelReq!!)
        }
    }

    /**
     * Generate a response for a given prompt with a provided model. This is a streaming endpoint, so there will be a series of responses. The final response object will include statistics and additional data from the request.
     */
    fun generate(OllamaRequest: OllamaGenerateRequest): OllamaGenerateResponse {
        var resultString: String = ""
        try {
            resultString = ApiConnectionFactory.getApiConnector().sendPost(
                "${this.urlBase}api/generate",
                Json.encodeToString(OllamaRequest)
            );
            val resultParsed: OllamaGenerateResponse = Json.decodeFromString<OllamaGenerateResponse>(resultString)
            return resultParsed
        } catch (ex: Exception) {
            this.resolveException(ex.message!!, modelReq = OllamaRequest.model)
            throw Exception("Error with reuqest [[${OllamaRequest.toString()}]]: ${ex.message}")
        }
    }

    /**
     * Generate the next message in a chat with a provided model. This is a streaming endpoint, so there will be a series of responses. Streaming can be disabled using "stream": false. The final response object will include statistics and additional data from the request.
     */
    fun chat(OllamaChatRequest: OllamaChatRequest): Any {
        TODO("Not implemented")
    }

    /**
     * Create a model from:
     *
     *     another model;
     *     a safetensors directory; or
     *     a GGUF file.
     *
     * If you are creating a model from a safetensors directory or from a GGUF file, you must create a blob for each of the files and then use the file name and SHA256 digest associated with each blob in the files field.
     */
    fun create(OllamaRequest: Any): Any {
        TODO("Not implemented")
    }

    /**
     * List models that are available locally.
     */
    fun tags(OllamaRequest: Any): Any {
        TODO("Not implemented")

    }

    /**
     * Show information about a model including details, modelfile, template, parameters, license, system prompt.
     */
    fun show(OllamaRequest: Any): Any {
        TODO("Not implemented")
    }


    /**
     * Push a file to the Ollama server to create a "blob" (Binary Large Object).
     */
    fun push(OllamaRequest: Any): Any {
        TODO("Not implemented")
    }


    /**
     * Copy a model. Creates a model with another name from an existing model.
     */
    fun copy(OllamaRequest: Any): Any {
        TODO("Not implemented")
    }

    /**
     * Delete a model and its data.
     */
    fun delete(OllamaRequest: OllamaDeleteRequest): Any {
        val resultString = ApiConnectionFactory.getApiConnector().sendDelete(
            "${this.urlBase}api/delete",
            Json.encodeToString(OllamaRequest)
        )
        return ""
    }

    /**
     * Download a model from the ollama library. Cancelled pulls are resumed from where they left off, and multiple calls will share the same download progress.
     */
    fun pull(OllamaPullRequest: OllamaPullRequest): OllamaPullResponse {
        val resultString = ApiConnectionFactory.getApiConnector().sendPost(
            "${this.urlBase}api/pull",
            Json.encodeToString(OllamaPullRequest)
        )
        val resultParsed: OllamaPullResponse = Json.decodeFromString<OllamaPullResponse>(resultString)
        return resultParsed
    }

    fun ensureActive() {
        Awaiters.awaitNotThrows({
            val tmp = this.version()

        }, Duration.ofMinutes(12), "Excpected ollam version to now throw")
    }

    /**
     * Generate embeddings from a model
     */
    fun embed(OllamaRequest: Any): Any {
        TODO("Not implemented")
    }

    /**
     * List models that are currently loaded into memory.
     */
    fun ps(OllamaRequest: Any): Any {
        TODO("Not implemented")
    }

    /**
     * Generate embeddings from a model
     */
    fun emeddings(OllamaRequest: Any): Any {
        TODO("Not implemented")
    }

    /**
     * Retrieve the Ollama version
     */
    fun version(): OllamaVersionResponse {
        val resultString = ApiConnectionFactory.getApiConnector().sendGet(
            "${this.urlBase}api/version"
        )
        val resultParsed: OllamaVersionResponse = Json.decodeFromString<OllamaVersionResponse>(resultString)
        return resultParsed
    }


}