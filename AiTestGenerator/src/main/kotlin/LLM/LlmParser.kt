package LLM

object LlmParser {

    fun extractListingFromLlmResponse(response: String): Collection<String> {
        val regexPatter: String = """
            ```[^"\\n]+\\n(.*?)```
        """.trimIndent()
        val regex = Regex(regexPatter, RegexOption.DOT_MATCHES_ALL)
        val matches = regex.findAll(response).map { it.groupValues[1].trim() }.toList()
        return matches
    }

    val test = ""
}