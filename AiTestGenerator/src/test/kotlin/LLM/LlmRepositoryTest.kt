package LLM


import LLM.Apis.Ollama.OllamaApiGenerator

import com.jetbrains.rd.generator.nova.PredefinedType
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.apache.tools.ant.taskdefs.Available
import org.codehaus.plexus.util.cli.Arg
import org.filomilo.AiTestGenerator.LLM.Apis.ApiConnectionFactory
import org.filomilo.AiTestGenerator.LLM.Containers.ContainersManager
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.filomilo.AiTestGenerator.LLM.LlmRepository

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.slf4j.LoggerFactory
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LlmRepositoryTest {

    companion object{
        val log = LoggerFactory.getLogger(LlmRepositoryTest.javaClass)
    }

    val containerManager: ContainersManager = DockerConnection
    var llmRepository: LlmRepository? = null


    @BeforeAll
    fun setup(){
        this.llmRepository = LlmRepository(
            containerManager,
            ApiConnectionFactory.getApiConnector(),
            OllamaApi = OllamaApiGenerator.getOllamaApi()
        )
        this.llmRepository!!.initlize();
    }

    fun llmProcessorProvide(): Stream<Arguments> {

        return return llmRepository!!.ListOfLlmProcessors
            .map { Arguments.of(it) }
            .stream()
    }


    @MethodSource("llmProcessorProvide")
    @ParameterizedTest
    fun promptEveryModel(llmProcesor: LLMProcessor) {
log.info("promptEveryModel:: ${llmProcesor}")
        llmProcesor.load()
                var answer: String = llmProcesor.executePrompt("why is sky blue?").response
//                assertNotNull(answer)
//                assertTrue(answer.isNotEmpty())
//                  llmProcesor.unload()



    }

    @MethodSource("llmProcessorProvide")
    @ParameterizedTest
    fun propmptFormatted(llmProcesor: LLMProcessor){
        @Serializable
        data class JsonDataFromLLmClass(
            val color: String,
            val available: Boolean
       )

        val reponse: LLMResponse= llmProcesor.executePrompt(
            "Waht color is grass",
            """
{
  "type": "object",
  "properties": {
    "color": {
      "type": "string",
      "enum": ["green", "red"]
    },
    "available": {
      "type": "boolean"
    }
  },
  "required": ["color", "available"]
}
            """.trimIndent()
        )
log.info(reponse.toString())

    val JsonDataFromLLmClass: JsonDataFromLLmClass= Json.decodeFromString<JsonDataFromLLmClass>(reponse.response)
 assertNotNull(JsonDataFromLLmClass)
assertEquals("green",JsonDataFromLLmClass.color)
    }



}