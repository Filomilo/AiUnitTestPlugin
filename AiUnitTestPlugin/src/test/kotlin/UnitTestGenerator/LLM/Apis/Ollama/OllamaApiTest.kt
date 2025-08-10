package UnitTestGenerator.LLM.Apis.Ollama

import Tools.Awaiters
import UnitTestGenerator.LLM.Containers.Docker.DockerConnection
import UnitTestGenerator.LLM.Containers.LLMContainers.OllamaContainer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.InetAddress
import kotlin.random.Random


//@Disabled("temporaryl")
class OllamaApiTest {
    //    var OllamaContainer: OllamaContainer? = null
    var ollamaPort: Int = 2325;
    var OllamaApi: OllamaApi? = null
    val model = "gemma3:1b"
    private val log: Logger = LoggerFactory.getLogger(OllamaApiTest::class.java)

    @BeforeEach
    fun setUp() {

        this.ollamaPort = Random.nextInt(10000, 20000)
//        this.OllamaContainer = OllamaContainer(
//            DockerConnection, port = ollamaPort, ramBytes = 1024L * 1024L * 1024L * 2L
//        )
//        this.OllamaContainer!!.start();
//        val address = InetAddress.getByName("ollama")
//        println(address.hostAddress)
//        log.info("host adress: ${address} ::::::: ${address.hostAddress}")
        this.OllamaApi = OllamaApi(
            "http://172.19.0.3:11434/"
        )
        Awaiters.awaitNotThrows(
            {
//                log.info("waiitng for ollama start:: \n\n[[[[[[[[[[[[[[[[[[[[[[\n ${this.OllamaContainer!!.getLogs()}\n\n]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]")
                this.OllamaApi!!.version();
//                log.info(
//                    "waiitng for ollama start:: \n\n[[[[[[[[[[[[[[[[[[[[[[\n ${this.OllamaContainer!!.getLogs()}\n" +
//                            "\n" +
//                            "]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]"
//                )

            },
            message = "Failed to wait for ollama api"
        )
        this.OllamaApi!!.ensureActive()

        this.OllamaApi!!.pull(OllamaPullRequest(model))
    }

    @AfterEach
    fun tearDown() {
//        this.OllamaContainer!!.destroy()
    }

    //    @Disabled("temporaryl")
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

    //    @Disabled("temporaryl")
    @Test
    fun generate() {
        assertDoesNotThrow {

            val OllamaGenerateResponse: OllamaGenerateResponse = this.OllamaApi!!.generate(
                OllamaGenerateRequest(
                    model = model,
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
                "I need you to provide me with the picture! I can't analyze an image without you showing it to me. \uD83D\uDE0A \n" +
                        "\n" +
                        "Please paste the image here, and I’ll do my best to tell you what's in it.",
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