package LLM

import DeviceSpecification
import okio.Path
import org.filomilo.AiTestGenerator.LLM.CachedLLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


class ExampleLlmWithResult : LLMProcessor {


    override fun executePrompt(
        prompt: String,
        jsonSchema: String?
    ): LLMResponse {
        return LLMResponse(
            prompt = prompt,
            response = "Result",
            modelName = "None",
            generationTime = 10.seconds,
            deviceSpecification = null,
            jsonFormat = jsonSchema
        )
    }

    override fun load() {

    }

    override fun unload() {

    }

    override fun getName(): String {
        return "_"
    }

    override fun getDeviceSpecification(): DeviceSpecification {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int {
        return 0
    }

}

class ExampleLlmWithoutResult : LLMProcessor {


    override fun executePrompt(
        prompt: String,
        jsonSchema: String?
    ): LLMResponse {
        return LLMResponse(
            prompt = "",
            response = TODO(),
            modelName = TODO(),
            generationTime = TODO(),
            deviceSpecification = TODO(),
            jsonFormat = TODO()
        )
    }

    override fun load() {
    }

    override fun unload() {
    }

    override fun getName(): String {
        return "_"
    }

    override fun getDeviceSpecification(): DeviceSpecification {
        TODO("Not yet implemented")
    }


    override fun hashCode(): Int {
        return 0
    }


}


class CachedLLMProcessorTest {

//    @AfterEach
//    fun tearDown() {
//        if(cacheFile.exists())
//        {
//            Files.delete(cacheFile)
//        }
//
//    }

    @Test
    fun executePrompt() {

        var llmWithResult: LLMProcessor = CachedLLMProcessor(ExampleLlmWithResult())
        var llmWitouthResult: LLMProcessor = CachedLLMProcessor(ExampleLlmWithoutResult())
        var result1: LLMResponse = llmWithResult.executePrompt("")
        var result2: LLMResponse = llmWitouthResult.executePrompt("")
        assertEquals("Result", result1.response)
        assertEquals(result1, result2.response)

    }


}