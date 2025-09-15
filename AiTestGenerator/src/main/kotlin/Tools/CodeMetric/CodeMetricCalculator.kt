package Tools.CodeMetric

import java.nio.file.Path

interface CodeMetricCalculator {
    fun calculateCodeMetricsForFiles(paths: List<Path>): ProjectMetricsReportMutlimetric
}