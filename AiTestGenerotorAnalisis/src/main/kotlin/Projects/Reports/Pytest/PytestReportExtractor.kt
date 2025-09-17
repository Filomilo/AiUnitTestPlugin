package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import Exceptions.ReportNotFoundException
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco.JacocoReport
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.ReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.slf4j.LoggerFactory
import java.nio.file.Path
import kotlin.io.path.exists

class PytestReportExtractor : ReportExtractor {
    companion object {
        val log = LoggerFactory.getLogger(PytestReportExtractor.javaClass)
        private val mapper: JsonMapper = JsonMapper()
    }


    override fun extractReport(projectPath: Path): TestReport {
        val pathToJson: Path = projectPath.resolve("coverage.json")
        if (!pathToJson.exists()) {
            log.error("Report not found coverage.json")
            throw ReportNotFoundException("Report not found coverage.json")
        }
        val jsonFileContent: String = pathToJson.toFile().readText()
        val pytestCoverageReport: PytestCoverageReport =
            mapper.readValue(jsonFileContent, PytestCoverageReport::class.java)
        return pytestCoverageReport;
    }

    override fun getReportFiles(projectPath: Path): Collection<Path> {
        return listOf(projectPath.resolve("htmlcov"), projectPath.resolve("coverage.json"))
    }
}