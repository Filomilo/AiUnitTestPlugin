package UnitTestGenerator.LLM.Apis.Ollama

import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled

class OllamaGenerateResponseTest {
    @Disabled("temporaryl")
    @Test
    fun testToString() {
        val json = """
            {
                "model": "llava",
                "created_at": "2025-08-02T12:00:02.166859586Z",
                "response": " The image shows a group of people standing together, with one individual holding up what appears to be a device or object. It looks like they could be attending an event or gathering, and the atmosphere seems friendly and casual. ",
                "done": true,
                "done_reason": "stop",
                "context": [733, 16289, 28793, 1824, 349, 297, 456, 5754, 28804, 733, 28748, 16289, 28793, 415, 3469, 4370, 264, 2071, 302, 905, 6328, 2553, 28725, 395, 624, 3235, 6632, 582, 767, 8045, 298, 347, 264, 3895, 442, 1928, 28723, 661, 4674, 737, 590, 829, 347, 19735, 396, 1951, 442, 17243, 28725, 304, 272, 13789, 3969, 10131, 304, 13316, 28723, 28705],
                "total_duration": 74099932065,
                "load_duration": 48240035762,
                "prompt_eval_count": 14,
                "prompt_eval_duration": 7229423002,
                "eval_count": 46,
                "eval_duration": 18594839268
            }
        """.trimIndent()
        val ExpectedValue: OllamaGenerateResponse = OllamaGenerateResponse(
            model = "llava",
            created_at = Instant.parse("2025-08-02T12:00:02.166859586Z"),
            response = " The image shows a group of people standing together, with one individual holding up what appears to be a device or object. It looks like they could be attending an event or gathering, and the atmosphere seems friendly and casual. ",
            done = true,
            done_reason = "stop",
            context = arrayOf(
                733,
                16289,
                28793,
                1824,
                349,
                297,
                456,
                5754,
                28804,
                733,
                28748,
                16289,
                28793,
                415,
                3469,
                4370,
                264,
                2071,
                302,
                905,
                6328,
                2553,
                28725,
                395,
                624,
                3235,
                6632,
                582,
                767,
                8045,
                298,
                347,
                264,
                3895,
                442,
                1928,
                28723,
                661,
                4674,
                737,
                590,
                829,
                347,
                19735,
                396,
                1951,
                442,
                17243,
                28725,
                304,
                272,
                13789,
                3969,
                10131,
                304,
                13316,
                28723,
                28705
            ).toList(),
            total_duration = 74099932065,
            load_duration = 48240035762,
            prompt_eval_count = 14,
            prompt_eval_duration = 7229423002,
            eval_count = 46,
            eval_duration = 18594839268
        )
        assertEquals(ExpectedValue, Json.decodeFromString<OllamaGenerateResponse>(json))

    }
}