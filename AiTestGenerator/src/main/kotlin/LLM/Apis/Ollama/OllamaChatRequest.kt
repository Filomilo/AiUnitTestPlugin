package org.filomilo.AiTestGenerator.LLM.Apis.Ollama


data class OllamaMessage(

    /**
     * the role of the message, either system, user, assistant, or tool
     */
    val role: String,

    /**
     * the content of the message
     */
    val content: String,
    /**
     * (for thinking models) the model's thinking process
     */
    val thinking: Boolean,

    /**
     * (optional): a list of images to include in the message (for multimodal models such as llava)
     */
    val images: List<String>? = null,

    /**
     * (optional): a list of tools in JSON that the model wants to use
     */
    val tool_calls: List<String>? = null,

    /**
     * (optional): add the name of the tool that was executed to inform the model of the result
     */
    val tool_name: String? = null
)


data class OllamaChatRequest(
    /**
     * (required) the model name
     */
    val model: String,

    /**
     * the messages of the chat, this can be used to keep a chat memory
     */
    val messages: List<OllamaMessage>,

    /**
     *list of tools in JSON for the model to use if supported
     */
    val tools: Any,

    /**
     * (for thinking models) should the model think before responding?
     */
    val think: Boolean? = null,

    /**
     * the format to return a response in. Format can be json or a JSON schema
     */
    val format: String? = null,

    /**
     *additional model parameters listed in the documentation for the Modelfile such as temperature
     */
    val options: String? = null,

    /**
     * if false the response will be returned as a single response object, rather than a stream of objects
     */
    val stream: Boolean = false,

    /**
     *controls how long the model will stay loaded into memory following the request (default: 5m)
     */
    val keep_alive: String? = null
) {}