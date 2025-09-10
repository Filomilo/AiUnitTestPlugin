package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports

import java.nio.file.Path

interface ReportExtractor {
    fun extractReport(projectPath: Path): TestReport
    fun getReportFiles(projectPath: Path): Collection<Path>
}