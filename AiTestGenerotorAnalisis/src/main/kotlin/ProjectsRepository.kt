package org.filomilo.AiTestGenerotorAnalisis

import java.nio.file.Paths

object ProjectsRepository {
    val projects: List<ProjectConfig> = listOf(
        ProjectConfig(
            name = "JavaCalculator",
            exampleProjectFolder = "JavaCalculator",
            projectType = ProjectTypes.JAVA_JUNIT_PROJECT
        ),
        ProjectConfig(
            name = "PythonCalculator",
            exampleProjectFolder = "PythonSimpleCalculator",
            projectType = ProjectTypes.PYTHON_PYTEST_PROJECT
        ),
    )
}