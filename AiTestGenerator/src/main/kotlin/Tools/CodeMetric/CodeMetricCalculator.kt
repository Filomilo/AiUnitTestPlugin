package Tools.CodeMetric

import java.nio.file.Path

interface CodeMetricCalculator {
    fun calculateCodeMetricsForDirectory(path: Path): ProjectMetricsReportMutlimetric
}