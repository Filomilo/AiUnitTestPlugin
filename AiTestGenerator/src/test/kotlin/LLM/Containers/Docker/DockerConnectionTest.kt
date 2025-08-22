package LLM.Containers.Docker


import org.filomilo.AiTestGenerator.LLM.Containers.Config.ContainerConfiguration
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DockerConnectionTest {

    val testContainer: String = "ubuntu"

    @Disabled("noImpemnted")
    @Test
    fun pullImage() {
    }

    @Disabled("noImpemnted")
    @Test
    fun getRunningVmList() {
    }

    @Disabled("noImpemnted")
    @Test
    fun getVmList() {
    }

    @Disabled("noImpemnted")
    @Test
    fun close() {
    }

    @Disabled("noImpemnted")
    @Test
    fun contianerListToIdList() {
    }

    //    @Disabled("temporaryl")
    @Test
    fun createVmCorrect() {

        assertDoesNotThrow {
            val amtOfConatinersBefore: Int = DockerConnection.getContainersList().size;
            var containerid: String = DockerConnection.createContainer(
                ContainerConfiguration(
                    image = testContainer,
                    ramBytes = 128 * 1024 * 1024
                )
            )
            val amtOfConatinersAfterCreation: Int = DockerConnection.getContainersList().size;

            DockerConnection.destroyContainer(containerid)
            val amtOfConatinersAfterestrcution: Int = DockerConnection.getContainersList().size;
            assertEquals(
                amtOfConatinersBefore + 1,
                amtOfConatinersAfterCreation,
                "New Vm failed to be created"
            )
            assertEquals(
                amtOfConatinersBefore,
                amtOfConatinersAfterestrcution,
                "New Vm failed to bew removed after creation"
            )
        }

    }
}