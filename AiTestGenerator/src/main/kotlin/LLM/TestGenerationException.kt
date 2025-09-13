package LLM

import Exceptions.LlmProcessingException

class TestGenerationException(override val message: String?) : LlmProcessingException(message) {
}