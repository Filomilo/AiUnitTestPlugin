package org.filomilo.AiTestGenerotorAnalisis

import org.filomilo.AiTestGenerator.Tools.System
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
    override fun run() {
        BufferedReader(InputStreamReader(inputStream)).lines()
            .forEach(consumer)
    }
}
object CommandExecutor {

    fun runCommand(command: String, path: Path) {
        val builder = ProcessBuilder()
        if (System.isWindows) {
            builder.command( "cmd.exe","/c",command)
        } else {
            builder.command("bash", "-c",command)
        }


        builder.directory(path.toFile())



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