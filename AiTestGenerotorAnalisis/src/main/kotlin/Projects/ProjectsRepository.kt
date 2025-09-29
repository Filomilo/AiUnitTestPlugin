package org.filomilo.AiTestGenerotorAnalisis.Projects

import Projects.ProjectTypes
import Tools.CodeParsers.PythonParser
import org.filomilo.AiTestGenerator.Tools.CodeParsers.JavaParser
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco.JacocoReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest.PytestReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Runners.MavenRunner
import org.filomilo.AiTestGenerotorAnalisis.Projects.Runners.PythonRunner
import Tools.PathResolver
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.name

object ProjectsRepository {

    fun getPathsFromGitIgnore(gitIgnorePath: Path): List<Path> {
        val list: MutableList<Path> = mutableListOf()
        Files.readAllLines(gitIgnorePath).forEach { x -> list.add(Path(x)) }
        list.add(Path(gitIgnorePath.name))
        return list
    }

    val projects: List<Project> = listOf(
        Project(
            name = "JavaCalculator",
            ProjectPath = PathResolver.resolveExampleProject("JavaCalculator"),
            projectRunner = MavenRunner(),
            reportExtractor = JacocoReportExtractor(),
            codeParser = JavaParser,
            testingFramework = "Junit5",
            codeFileExtension = "java",
            ignoredPaths = listOf(Path(".idea"), Path("target"), Path(".gitignore"))
        ),
        Project(
            name = "PythonCalculator",
            ProjectPath = PathResolver.resolveExampleProject("PythonSimpleCalculator"),
            projectRunner = PythonRunner("calculator"),
            reportExtractor = PytestReportExtractor(),
            testingFramework = "pytest",
            codeParser = PythonParser(),
            codeFileExtension = "py",
            ignoredPaths = listOf(
                Path("tests").resolve("__pycache__"),
                Path(".idea"),
                Path(".gitignore"),
                Path(".pytest_cache"),
                Path("__pycache__"),
                Path("calculator").resolve("__pycache__"),
                Path("calculator").resolve(".idea")
            )
        ),
    )
}