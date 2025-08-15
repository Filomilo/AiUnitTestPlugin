import org.filomilo.AiTestGenerotorAnalisis.ProjectConfig
import org.filomilo.AiTestGenerotorAnalisis.ProjectTypes
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.exists

class ProjectConfigTest {

    @Test
    fun PathConfig() {

        assertDoesNotThrow {
            val projectConfig = ProjectConfig(
                "exmaple",
                "JavaCalculator",
                ProjectTypes.PYTHON_PYTEST_PROJECT
            )
        }


        assertThrows<IllegalArgumentException> {
            val projectConfig = ProjectConfig(
                "exmaple",
                "notExisitng",
                ProjectTypes.PYTHON_PYTEST_PROJECT
            )
        }

    }

}