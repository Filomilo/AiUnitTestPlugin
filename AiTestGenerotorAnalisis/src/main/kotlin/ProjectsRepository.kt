package org.filomilo.AiTestGenerotorAnalisis

import Projects.ProjectTypes
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project

object ProjectsRepository {
    val projects: List<Project> = listOf(
        Project(
            name = "JavaCalculator",
            ProjectPath = PathResolver.resolveExampleProject("JavaCalculator"),
            projectType = ProjectTypes.JAVA_JUNIT_PROJECT
        ),
        Project(
            name = "PythonCalculator",
            ProjectPath = PathResolver.resolveExampleProject("PythonSimpleCalculator"),
            projectType = ProjectTypes.PYTHON_PYTEST_PROJECT
        ),
    )
}