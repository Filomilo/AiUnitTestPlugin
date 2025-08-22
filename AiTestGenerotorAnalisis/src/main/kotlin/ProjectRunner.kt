package org.filomilo.AiTestGenerotorAnalisis

import java.nio.file.Path

interface ProjectRunner {
    fun runTests(projectPath: Path)
}