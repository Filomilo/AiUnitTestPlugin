package org.filomilo.AiTestGenerotorAnalisis.Projects

import Projects.ProjectTypes
import Tools.CodeParsers.PythonParser
import org.filomilo.AiTestGenerator.Tools.CodeParsers.JavaParser
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco.JacocoReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest.PytestReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Runners.MavenRunner
import org.filomilo.AiTestGenerotorAnalisis.Projects.Runners.PythonRunner
import Tools.PathResolver
import kotlin.io.path.Path

object ProjectsRepository {
    val projects: List<Project> = listOf(
        Project(
            name = "JavaCalculator",
            ProjectPath = PathResolver.resolveExampleProject("JavaCalculator"),
            projectRunner = MavenRunner(),
            reportExtractor = JacocoReportExtractor(),
            codeParser = JavaParser,
            testingFramework = "Junit5",
            codeFileExtension = "java"
        ),
        Project(
            name = "PythonCalculator",
            ProjectPath = PathResolver.resolveExampleProject("PythonSimpleCalculator"),
            projectRunner = PythonRunner("calculator"),
            reportExtractor = PytestReportExtractor(),
            testingFramework = "Pytest",
            codeParser = PythonParser(),
            codeFileExtension = "py",
            ignoredFiles = listOf(Path("tests").resolve("__pycache__"))
        ),
    )
}