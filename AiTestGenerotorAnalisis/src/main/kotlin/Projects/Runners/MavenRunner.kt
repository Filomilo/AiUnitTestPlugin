package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import org.filomilo.AiTestGenerotorAnalisis.Tools.CommandExecutor
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectRunner
import java.nio.file.Path

class MavenRunner(): ProjectRunner {







    override fun runTests(projectPath: Path) {
        CommandExecutor.runCommand("mvn clean install", projectPath)
    }




}