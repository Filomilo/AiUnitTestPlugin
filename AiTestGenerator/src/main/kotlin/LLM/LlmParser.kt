package LLM

object LlmParser {

    fun extractListingFromLlmResponse(response: String, languageFilter: String): Collection<String> {
        val regexPatter: String = """
            ```$languageFilter\\n(.*?)```
        """.trimIndent()
        val regex = Regex(regexPatter, RegexOption.DOT_MATCHES_ALL)
        val matches = regex.findAll(response).map { it.groupValues[1].trim() }.toList()
        return matches
    }

    val test = ""
}