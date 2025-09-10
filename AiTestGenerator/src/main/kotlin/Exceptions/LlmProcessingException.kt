package Exceptions

import kotlinx.serialization.Serializable

@Serializable
open class LlmProcessingException(override val message: String?) : Exception(message) {
}