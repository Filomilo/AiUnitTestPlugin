package UnitTestGenerator.LLM.Containers.LLMContainers

import UnitTestGenerator.LLM.Containers.Docker.DockerConnection
import org.junit.Ignore
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class OllamaContainerTest {

    var OllamaContainer: OllamaContainer? = null
    private val log: Logger = LoggerFactory.getLogger(OllamaContainerTest::class.java)

    @BeforeEach
    fun setUp() {
        var amountOFContainers: Int = DockerConnection.getContainersList().size;
        this.OllamaContainer = OllamaContainer(DockerConnection);
        assertNotNull(this.OllamaContainer)
        var amountOFContainersAfter: Int = DockerConnection.getContainersList().size;
        assertEquals(
            amountOFContainersAfter,
            amountOFContainers + 1,
            "Expetcted amount of contaienrts to increase but there are ${amountOFContainersAfter} containers and expeccted ${amountOFContainers + 1}, containres avaivlebes are ${
                DockerConnection.getContainersList().stream().toArray().joinToString { ",${System.lineSeparator()}" }
            }"
        )
    }

    @AfterEach
    fun tearDown() {
        var amountOFContainers: Int = DockerConnection.getContainersList().size;
        this.OllamaContainer!!.destroy();
        var amountOFContainersAfter: Int = DockerConnection.getContainersList().size;
        assertEquals(amountOFContainersAfter, amountOFContainers - 1)
    }

    //    @Disabled("temporaryl")
    @Test
    fun start() {
        log.info("Startt")
        log.info("Debug")

        org.junit.jupiter.api.assertDoesNotThrow() {

            this.OllamaContainer!!.start();
            assertEquals(11434, this.OllamaContainer!!.getOpenPort())
            this.OllamaContainer!!.stop();
        }
    }
}