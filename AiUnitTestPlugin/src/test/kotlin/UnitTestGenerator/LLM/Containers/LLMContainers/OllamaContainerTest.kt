package UnitTestGenerator.LLM.Containers.LLMContainers

import UnitTestGenerator.LLM.Containers.Docker.DockerConnection
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class OllamaContainerTest {

    var OllamaContainer: OllamaContainer? = null

    @BeforeEach
    fun setUp() {
        var amountOFContainers: Int = DockerConnection.getContainersList().size;
        this.OllamaContainer = OllamaContainer(DockerConnection);
        assertNotNull(this.OllamaContainer)
        var amountOFContainersAfter: Int = DockerConnection.getContainersList().size;
        assertEquals(amountOFContainersAfter, amountOFContainers + 1)
    }

    @AfterEach
    fun tearDown() {
        var amountOFContainers: Int = DockerConnection.getContainersList().size;
        this.OllamaContainer!!.destroy();
        var amountOFContainersAfter: Int = DockerConnection.getContainersList().size;
        assertEquals(amountOFContainersAfter, amountOFContainers - 1)
    }

    @Test
    fun start() {
        org.junit.jupiter.api.assertDoesNotThrow() {
            this.OllamaContainer!!.start();
            assertEquals(11434, this.OllamaContainer!!.getOpenPort())
            this.OllamaContainer!!.stop();
        }
    }
}