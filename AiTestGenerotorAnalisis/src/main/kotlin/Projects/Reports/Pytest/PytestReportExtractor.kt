package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco.JacocoReport
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.ReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import java.nio.file.Path

class PytestReportExtractor : ReportExtractor {
    companion object {
        private val mapper: JsonMapper = JsonMapper()
    }


    override fun extractReport(projectPath: Path): TestReport {
        val pathToJson: Path = projectPath.resolve("coverage.json")
        val jsonFileContent: String = pathToJson.toFile().readText()
        val pytestCoverageReport: PytestCoverageReport =
            mapper.readValue(jsonFileContent, PytestCoverageReport::class.java)
        return pytestCoverageReport;
    }

    override fun getReportFiles(projectPath: Path): Collection<Path> {
        return listOf(projectPath.resolve("htmlcov"), projectPath.resolve("coverage.json"))
    }
}