package CodeMetric

import Tools.CodeMetric.MultiMetricCodeMetricCalculator
import Tools.CodeMetric.ProjectMetricsReportMutlimetric
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectsRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import java.nio.file.Path

class MultiMetricCodeMetricCalculatorTest {

    @Disabled("not yet implented")
    @Test
    fun calculateCodeMetricsForDirectory() {
        var multiMetricCodeMetricCalculator: MultiMetricCodeMetricCalculator = MultiMetricCodeMetricCalculator()
        assertDoesNotThrow {
            var path: Path = ProjectsRepository.projects.get(1).ProjectPath.resolve("tests")
            var CodeMetricReport: ProjectMetricsReportMutlimetric =
                multiMetricCodeMetricCalculator.calculateCodeMetricsForDirectory(
                    path.toAbsolutePath()
                )
            assertNotNull(CodeMetricReport)
        }
    }
}