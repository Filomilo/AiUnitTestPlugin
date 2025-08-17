package LLM.Apis.Ollama

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.filomilo.AiTestGenerator.LLM.Apis.Ollama.OllamaPullRequest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled

class OllamaPullRequestTest {

    @Test
//    @Disabled("temporaryl")
    fun testToJson() {
        val json: String = Json.encodeToString(OllamaPullRequest(model = "test", stream = false))
        val expectedJson: String = """
            {
            "model": "test",
            "insecure": false,
            "stream": false
            }
        """.lines().joinToString("").trimIndent().trimEnd().trimMargin().replaceIndent("").replace(Regex("\\s+"), "")

        assertEquals(
            expectedJson, json.format(json)
        )
    }
}