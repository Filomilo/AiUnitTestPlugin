package UnitTestGenerator.LLM.Apis.Ollama

import UnitTestGenerator.LLM.Containers.Docker.DockerConnection
import UnitTestGenerator.LLM.Containers.LLMContainers.OllamaContainer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import kotlin.random.Random

class OllamaApiTest {
    var OllamaContainer: OllamaContainer? = null
    var ollamaPort: Int = 2325;
    var OllamaApi: OllamaApi? = null

    @BeforeEach
    fun setUp() {
        this.ollamaPort = Random.nextInt(10000, 20000)
        this.OllamaContainer = OllamaContainer(
            DockerConnection, port = ollamaPort
        )
        this.OllamaContainer!!.start();
        this.OllamaApi = OllamaApi(
            "http://localhost:$ollamaPort/"
        )
        this.OllamaApi!!.ensureActive()

        this.OllamaApi!!.pull(OllamaPullRequest("llava"))
    }

    @AfterEach
    fun tearDown() {
        this.OllamaContainer!!.destroy()
    }

    @Test
    fun generateNotExisitngModel() {
        assertThrows(NotExisitingModel::class.java) {
            this.OllamaApi!!.generate(
                OllamaGenerateRequest(
                    model = "NonExisitng"
                )
            )
        }
    }

    @Test
    fun generate() {
        assertDoesNotThrow {

            val OllamaGenerateResponse: OllamaGenerateResponse = this.OllamaApi!!.generate(
                OllamaGenerateRequest(
                    model = "llava",
                    prompt = "What is in this picture?",
                    stream = false,
                    options = JsonObject(
                        mapOf(
                            "seed" to JsonPrimitive(1)
                        )
                    )
                )
            )
            assertNotNull(OllamaGenerateResponse)
            assertEquals(
                " This is an image of a wall with some text on it, which appears to be in a language that uses non-Latin script. The image does not show any specific objects or people that can be confidently identified without additional context. ",
                OllamaGenerateResponse.response
            )
        }
    }

    @Disabled("Not yet implemented")
    @Test
    fun chat() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun create() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun tags() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun show() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun testCreate() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun push() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun testTags() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun testShow() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun copy() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun delete() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun pull() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun testPush() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun embed() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun ps() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun emeddings() {
    }

    @Disabled("Not yet implemented")
    @Test
    fun version() {
    }
}