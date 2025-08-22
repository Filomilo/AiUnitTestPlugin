package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import java.nio.file.Path

interface TestRunner {
    fun runTestOnProject(projectPath:Path)
}