package Exceptions

class ReportNotFoundException(override val message: String?) : LlmProcessingException(message) {
}