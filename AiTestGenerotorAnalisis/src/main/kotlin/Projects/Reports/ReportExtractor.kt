package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports

import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import java.nio.file.Path

interface ReportExtractor {
    fun extractReport(projectPath: Path): TestReport
    fun getReportFiles(projectPath: Path): Collection<Path>
    fun clearFiles(project: Project)
}