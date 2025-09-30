package LLM

import DeviceSpecification
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.time.Duration

class LLMResponseSerializerTest {
    @Test
    fun deserialize() {
        val llmRepsonseORign = LLMResponse(
            prompt = "Prompt",
            response = "RESPONSE",
            modelName = "MODEL_NAME",
            generationTime = Duration.ZERO,
            deviceSpecification = DeviceSpecification.getCurrentDeviceSpecification(),
            jsonFormat = "JSON FORMAT"
        )

        val serailezed: String = Json.encodeToString(llmRepsonseORign)

        val llmRepsonseDeserilzed = Json.decodeFromString<LLMResponse>(serailezed)
        assertEquals(llmRepsonseORign, llmRepsonseDeserilzed)
        assertTrue(
            llmRepsonseDeserilzed.compareConfig(
                prompt = llmRepsonseORign.prompt,
                modelName = llmRepsonseORign.modelName,
                device = llmRepsonseORign.deviceSpecification,
                jsonFormat = llmRepsonseORign.jsonFormat
            )
        )
    }

}