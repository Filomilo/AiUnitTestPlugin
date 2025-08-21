package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import org.filomilo.AiTestGenerator.Tools.System
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




    class StreamGobbler(private val inputStream: InputStream, private val consumer: Consumer<String>) :
        Runnable {
        override fun run() {
            BufferedReader(InputStreamReader(inputStream)).lines()
                .forEach(consumer)
        }
    }


    override fun runTests(projectPath: Path) {
        val builder = ProcessBuilder()
        if (System.isWindows) {
//            builder.command("cmd.exe", "mvn")
            builder.command( "cmd.exe","/c", "mvn clean install")
//            builder.command("cmd.exe", "/c", "dir");
        } else {
            builder.command("bash", "-c","mvn clean install")
        }


        builder.directory(projectPath.toFile())



        val process = builder.start()
        val executorService: ExecutorService = Executors.newFixedThreadPool(2)

        val streamGobbler =
            StreamGobbler(process.inputStream,
                Consumer<String> { x: String? -> println(x) })
        val future: Future<*> = executorService.submit(streamGobbler)


        val exitCode = process.waitFor(20, TimeUnit.MINUTES)
        println(exitCode)
    }




}