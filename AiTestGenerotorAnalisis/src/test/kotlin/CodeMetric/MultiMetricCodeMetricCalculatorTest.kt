package CodeMetric

import Tools.CodeMetric.MultiMetricCodeMetricCalculator
import Tools.CodeMetric.ProjectMetricsReportMutlimetric
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectsRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS
import java.nio.file.Path

@EnabledOnOs(OS.LINUX)
class MultiMetricCodeMetricCalculatorTest {


    @Test
    fun calculateCodeMetricsForPythonFiles() {
        var multiMetricCodeMetricCalculator: MultiMetricCodeMetricCalculator = MultiMetricCodeMetricCalculator()
        assertDoesNotThrow {
            var path: Path = ProjectsRepository.projects.get(1).ProjectPath.resolve("tests")
            val testFiles: List<Path> = ProjectsRepository.projects.get(1).getTestFiles()
            var CodeMetricReport: ProjectMetricsReportMutlimetric =
                multiMetricCodeMetricCalculator.calculateCodeMetricsForFiles(
                    testFiles
                )
            assertNotNull(CodeMetricReport)
        }
    }


    @Test
    fun calculateCodeMetricsForJavaFiles() {
        var multiMetricCodeMetricCalculator: MultiMetricCodeMetricCalculator = MultiMetricCodeMetricCalculator()
        assertDoesNotThrow {
            val testFiles: List<Path> = ProjectsRepository.projects.get(0).getTestFiles()
            var CodeMetricReport: ProjectMetricsReportMutlimetric =
                multiMetricCodeMetricCalculator.calculateCodeMetricsForFiles(
                    testFiles
                )
            assertNotNull(CodeMetricReport)
        }
    }


}