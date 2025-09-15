package Exceptions

class ApiPromptingException(override val message: String?) : LlmProcessingException(message) {
}