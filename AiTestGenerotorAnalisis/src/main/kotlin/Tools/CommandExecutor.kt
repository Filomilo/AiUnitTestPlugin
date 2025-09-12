package org.filomilo.AiTestGenerotorAnalisis.Tools

import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.Tools.System
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Path
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class StreamGobbler(private val inputStream: InputStream, private val consumer: Consumer<String>) :

    Runnable {
    private val log: Logger = LoggerFactory.getLogger(CommandExecutor::class.java)

    override fun run() {
        BufferedReader(InputStreamReader(inputStream)).lines()
            .forEach(consumer)
    }
}

object CommandExecutor {
    private val log: Logger = LoggerFactory.getLogger(CommandExecutor::class.java)

    fun runCommand(command: String, path: Path): String {
        log.info("executing command [[$command]] in location [[${path.toAbsolutePath()}]]")
        val builder = ProcessBuilder()
        if (System.isWindows) {
            builder.command("cmd.exe", "/c", command)
        } else {
            builder.command("bash", "-c", command)
        }


        builder.directory(path.toFile())


        val process = builder.start()
        val executorService: ExecutorService = Executors.newFixedThreadPool(2)
        val stringBuilder: StringBuilder = StringBuilder()
        val streamGobbler =
            StreamGobbler(
                process.inputStream,
                Consumer<String> { x: String? -> println(x); log.info(x); stringBuilder.append(x + "\n") })
        val future: Future<*> = executorService.submit(streamGobbler)


        val exitCode = process.waitFor(20, TimeUnit.MINUTES)
        process.destroy()
        executorService.shutdown()
        println(exitCode)
        return stringBuilder.toString()
    }
}