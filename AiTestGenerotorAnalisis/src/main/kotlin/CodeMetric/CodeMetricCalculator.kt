package org.filomilo.AiTestGenerotorAnalisis.CodeMetric

import java.nio.file.Path

interface CodeMetricCalculator {
    fun calculateCodeMetricsForDirectory(path: Path):CodeMetricReport
}