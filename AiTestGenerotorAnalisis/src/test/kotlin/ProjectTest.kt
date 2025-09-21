import Projects.ProjectTypes
import org.filomilo.AiTestGenerator.Tools.CodeParsers.JavaParser
import Tools.PathResolver
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest.PytestReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Runners.PythonRunner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ProjectTest {

    @Test
    fun PathConfig() {

        assertDoesNotThrow {
            val project = Project(
                name = "PythonCalculator",
                ProjectPath = PathResolver.resolveExampleProject("JavaCalculator"),
                projectRunner = PythonRunner("calculator"),
                reportExtractor = PytestReportExtractor(),
                testingFramework = "Pytest",
                codeParser = JavaParser,
                codeFileExtension = "py",
                ignoredPaths = emptyList()
            )
        }


        assertThrows<IllegalArgumentException> {
            val project = Project(

                name = "PythonCalculator",
                ProjectPath = PathResolver.resolveExampleProject("notExisitng"),
                projectRunner = PythonRunner("calculator"),
                reportExtractor = PytestReportExtractor(),
                testingFramework = "Pytest",
                codeParser = JavaParser,
                codeFileExtension = "py",
                ignoredPaths = emptyList(),
            )
        }

    }

}