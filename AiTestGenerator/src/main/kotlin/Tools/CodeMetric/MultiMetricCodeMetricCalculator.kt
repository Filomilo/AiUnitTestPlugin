package Tools.CodeMetric

import Tools.CommandExecutor
import kotlinx.serialization.json.Json
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.slf4j.LoggerFactory
import java.nio.file.Path
import kotlin.io.path.absolutePathString


class MultiMetricCodeMetricCalculator : CodeMetricCalculator {
    companion object {
        val log = LoggerFactory.getLogger(MultiMetricCodeMetricCalculator.javaClass)
    }


    override fun calculateCodeMetricsForFiles(paths: List<Path>): ProjectMetricsReportMutlimetric {
        val filteredFiles: List<Path> = paths.filter { x -> x.toFile().length() > 0 }

        val commandBuilder: StringBuilder = StringBuilder()
        commandBuilder.append("multimetric ");
        if (filteredFiles.count() > 1) {
            commandBuilder.append("files ");
        }
        commandBuilder.append(filteredFiles.map { x -> "\"${x.absolutePathString()}\"" }.joinToString(" "))
        val command: String = commandBuilder.toString()
        log.info("mulitmetic ocmmad [[$command]]")


        val response: String = CommandExecutor.runCommand(command)
        log.info("mulitmetic ocmmad response [[$response]]")
        return Json.decodeFromString<ProjectMetricsReportMutlimetric>(response)
    }
}