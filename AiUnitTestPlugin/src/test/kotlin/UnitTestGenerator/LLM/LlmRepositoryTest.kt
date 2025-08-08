package UnitTestGenerator.LLM

import UnitTestGenerator.LLM.Apis.ApiConnectionFactory
import UnitTestGenerator.LLM.Containers.ContainersManager
import UnitTestGenerator.LLM.Containers.Docker.DockerConnection
import org.junit.Ignore
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
            ApiConnectionFactory.getApiConnector()
        )
        this.llmRepository!!.initlize();

    }

    @AfterEach
    fun tearDown() {
        this.containerManager.destroyAll()

    }

    @Test
    @Ignore("temporaryl")
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