package LLM


import LLM.Apis.Ollama.OllamaApiGenerator
import org.filomilo.AiTestGenerator.LLM.Apis.ApiConnectionFactory
import org.filomilo.AiTestGenerator.LLM.Containers.ContainersManager
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.LLM.LlmRepository

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class LlmRepositoryTest {

    val containerManager: ContainersManager = DockerConnection
    var llmRepository: LlmRepository? = null

    @BeforeEach
    fun setUp() {
        this.llmRepository = LlmRepository(
            containerManager,
            ApiConnectionFactory.getApiConnector(),
            OllamaApi = OllamaApiGenerator.getOllamaApi()
        )
        this.llmRepository!!.initlize();

    }

    @AfterEach
    fun tearDown() {

    }

    @Test
//    @Disabled("temporaryl")
    fun promptEveryModel() {
        org.junit.jupiter.api.assertDoesNotThrow {
            this.llmRepository!!.ListOfLlmProcessors.forEach { x ->
                x.load()
                var answer: String = x.executePrompt("why is sky blue?")
                assertNotNull(answer)
                assertTrue(answer.isNotEmpty())
                x.unload()
            }
        }

    }
}