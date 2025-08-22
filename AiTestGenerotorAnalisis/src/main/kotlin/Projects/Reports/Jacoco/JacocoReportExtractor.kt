package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.ReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import java.nio.file.Path

class JacocoReportExtractor: ReportExtractor {
    companion object {
        private val mapper: XmlMapper = XmlMapper()
    }

    override fun extractReport(projectPath: Path): TestReport {
     val  pathToXml: Path= projectPath.resolve("target").resolve("site").resolve("jacoco").resolve("jacoco.xml")
        val xmlFileConten: String= pathToXml.toFile().readText()
        val JacocoReport: JacocoReport =   mapper.readValue(xmlFileConten, JacocoReport::class.java)
        return JacocoReport;
    }
}