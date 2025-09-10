package LLM

import DeviceSpecification
import okio.Path
import org.filomilo.AiTestGenerator.LLM.CachedLLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists


class ExampleLlmWithResult : LLMProcessor {
    override fun executePrompt(prompt: String): String {
        return "Result"
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
    override fun executePrompt(prompt: String): String {
        return ""
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
        var result1: String = llmWithResult.executePrompt("")
        var result2: String = llmWitouthResult.executePrompt("")
        assertEquals("Result", result1)
        assertEquals(result1, result2)

    }


}