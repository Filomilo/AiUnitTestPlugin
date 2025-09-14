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

    override fun calculateCodeMetricsForDirectory(path: Path): ProjectMetricsReportMutlimetric {
        val filesInDirectory: Collection<Path> = FilesManagment.getFilesRecursively(path)
        val command: String =
            "multimetric files ${filesInDirectory.map { x -> "\"$x.absolutePathString()\"" }.joinToString(" ")}"
        log.info("mulitmetic ocmmad [[$command]]")
        val response: String = CommandExecutor.runCommand(command)
        log.info("mulitmetic ocmmad response [[$response]]")
        return Json.decodeFromString<ProjectMetricsReportMutlimetric>(response)
    }
}