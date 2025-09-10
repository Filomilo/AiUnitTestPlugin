package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import org.filomilo.AiTestGenerotorAnalisis.Tools.CommandExecutor
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectRunner
import java.nio.file.Path

class PythonRunner(val projectName: String): ProjectRunner {
    override fun runTests(projectPath: Path) {
        CommandExecutor.runCommand(" pytest tests/ --cov=${projectName} --cov-report json\n", projectPath)
    }
}