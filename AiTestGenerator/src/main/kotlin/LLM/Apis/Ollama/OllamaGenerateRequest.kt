package org.filomilo.AiTestGenerator.LLM.Apis.Ollama

import kotlinx.serialization.Contextual
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
@Serializable
data class OllamaGenerateRequest(
    /**
     * (required) the model name
     */
    val model: String,

    /**
     * the prompt to generate a response for
     */
    @EncodeDefault(EncodeDefault.Mode.ALWAYS) val prompt: String = "",

    /**
     * the text after the model response
     */
    val suffix: String? = null,

    /**
     * (optional) a list of base64-encoded images (for multimodal models such as llava)
     */
    val images: List<String>? = null,

    /**
     * (for thinking models) should the model think before responding?
     */
    val think: Boolean? = null,

    ///////////////// ADVANCED ///////////////////////

    /**
     * the format to return a response in. Format can be json or a JSON schema
     */
    @Contextual var format: String? = null,

    /**
     * additional model parameters listed in the documentation for the Modelfile such as temperature
     */
    @Contextual val options: JsonObject? = null,

    /**
     * system message to (overrides what is defined in the Modelfile)
     */
    @Contextual val system: String? = null,

    /**
     * the prompt template to use (overrides what is defined in the Modelfile)
     */
    @Contextual val template: String? = null,

    /**
     * if false the response will be returned as a single response object, rather than a stream of objects
     */
    val stream: Boolean? = null,

    /**
     * if true no formatting will be applied to the prompt. You may choose to use the raw parameter if you are specifying a full templated prompt in your request to the API
     */
    val raw: Boolean? = null,

    /**
     * controls how long the model will stay loaded into memory following the request (default: 5m)
     */
    val keep_alive: Boolean? = null
) {
//    override fun toString(): String {
//        return "OllamaGenerateRequest(prompt='$prompt', model='$model')"
//    }
}
