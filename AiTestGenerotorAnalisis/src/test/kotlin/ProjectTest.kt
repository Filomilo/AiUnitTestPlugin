import Projects.ProjectTypes
import org.filomilo.AiTestGenerotorAnalisis.PathResolver
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ProjectTest {

    @Test
    fun PathConfig() {

        assertDoesNotThrow {
            val project = Project(
                "exmaple",
                PathResolver.resolveExampleProject("JavaCalculator"),
                ProjectTypes.PYTHON_PYTEST_PROJECT
            )
        }


        assertThrows<IllegalArgumentException> {
            val project = Project(
                "exmaple",
                PathResolver.resolveExampleProject("notExisitng"),
                ProjectTypes.PYTHON_PYTEST_PROJECT
            )
        }

    }

}