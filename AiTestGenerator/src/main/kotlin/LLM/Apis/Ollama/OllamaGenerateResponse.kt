package org.filomilo.AiTestGenerator.LLM.Apis.Ollama;

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.datetime.serializers.InstantIso8601Serializer
@Serializable
data class OllamaGenerateResponse(
    var model: String,
    @Serializable(with = InstantIso8601Serializer::class)
    var created_at: Instant,

    /** Empty if the response was streamed; if not, this will contain the full response */
    var response: String = "",

    var done: Boolean = false,
    var done_reason: String,

    /** An encoding of the conversation used in this response, for keeping conversational memory */
    var context: List<Int> = emptyList<Int>(),

    /** Time spent generating the response */
    var total_duration: Long = 0L,

    /** Time spent in nanoseconds loading the model */
    var load_duration: Long = 0L,

    /** Number of tokens in the prompt */
    var prompt_eval_count: Int = 0,

    /** Time spent in nanoseconds evaluating the prompt */
    var prompt_eval_duration: Long = 0L,

    /** Number of tokens in the response */
    var eval_count: Int = 0,

    /** Time in nanoseconds spent generating the response */
    var eval_duration: Long = 0L
)