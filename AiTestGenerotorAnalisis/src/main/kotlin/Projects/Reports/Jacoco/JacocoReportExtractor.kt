package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import Exceptions.ReportNotFoundException
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.ReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import java.nio.file.Path
import kotlin.io.path.notExists

class JacocoReportExtractor : ReportExtractor {
    companion object {
        private val mapper: XmlMapper = XmlMapper()
    }

    override fun extractReport(projectPath: Path): TestReport {
        val pathToXml: Path = projectPath.resolve("target").resolve("site").resolve("jacoco").resolve("jacoco.xml")
        if (pathToXml.notExists()) {
            throw ReportNotFoundException(
                """
                    Couldn't find jacoco report in location:: $pathToXml
                """.trimIndent()
            )
        }
        val xmlFileConten: String = pathToXml.toFile().readText()
        val JacocoReport: JacocoReport = mapper.readValue(xmlFileConten, JacocoReport::class.java)
        return JacocoReport;
    }

    override fun getReportFiles(projectPath: Path): Collection<Path> {
        return listOf<Path>(projectPath.resolve("target"))
    }

    override fun clearFiles(project: Project) {
        FilesManagment.deleteFilse(this.getReportFiles(project.ProjectPath))
    }
}