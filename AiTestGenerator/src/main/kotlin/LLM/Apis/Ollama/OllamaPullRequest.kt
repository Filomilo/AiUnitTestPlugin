package org.filomilo.AiTestGenerator.LLM.Apis.Ollama

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
@Serializable
data class OllamaPullRequest(

    /**
     * name of the model to pull
     */
    var model: String,

    /**
     * (optional) allow insecure connections to the library. Only use this if you are pulling from your own library during development.
     */
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) var insecure: Boolean = false,

    /**
     * (optional) if false the response will be returned as a single response object, rather than a stream of objects
     */
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) var stream: Boolean = false,
)