package UnitTestGenerator.LLM.Apis.Ollama

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled

class OllamaGenerateRequestTest {
    //    @Disabled("temporaryl")
    @Test
    fun testToJson() {


        val json: String = Json.encodeToString(
            OllamaGenerateRequest(
                model = "modelName",
                prompt = "testPrompt"
            )
        )
        val expectedJson: String = """
            {
            "model": "modelName",
            "prompt": "testPrompt"
            }
        """.lines().joinToString("").trimIndent().trimEnd().trimMargin().replaceIndent("").replace(Regex("\\s+"), "")

        assertEquals(
            expectedJson, json.format(json)
        )
    }
}