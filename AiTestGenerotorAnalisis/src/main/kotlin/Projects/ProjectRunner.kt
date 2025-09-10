package org.filomilo.AiTestGenerotorAnalisis.Projects

import java.nio.file.Path

interface ProjectRunner {
    fun runTests(projectPath: Path)
}