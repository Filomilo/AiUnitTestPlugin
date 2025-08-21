package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import org.filomilo.AiTestGenerator.Tools.System
import org.filomilo.AiTestGenerotorAnalisis.CommandExecutor
import org.filomilo.AiTestGenerotorAnalisis.ProjectRunner
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Path
import java.util.concurrent.Future
import java.util.function.Consumer
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

class MavenRunner(): ProjectRunner {







    override fun runTests(projectPath: Path) {
        CommandExecutor.runCommand("mvn clean install", projectPath)
    }




}