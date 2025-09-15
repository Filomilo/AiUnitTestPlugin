package Tools.CodeMetric

import kotlinx.serialization.Serializable

@Serializable
data class ProjectMetricsReportMutlimetric(
    val files: Map<String, FileMetrics>,
    val overall: Metrics,
    val stats: Stats
)

@Serializable
data class FileMetrics(
    val comment_ratio: Double,
    val cyclomatic_complexity: Int,
    val fanout_external: Int,
    val fanout_internal: Int,
    val halstead_bugprop: Double,
    val halstead_difficulty: Double,
    val halstead_effort: Double,
    val halstead_timerequired: Double,
    val halstead_volume: Double,
    val lang: List<String>,
    val loc: Int,
    val maintainability_index: Double,
    val operands_sum: Int,
    val operands_uniq: Int,
    val operators_sum: Int,
    val operators_uniq: Int,
    val pylint: Double,
    val tiobe: Double,
    val tiobe_compiler: Double,
    val tiobe_complexity: Double,
    val tiobe_coverage: Double,
    val tiobe_duplication: Double,
    val tiobe_fanout: Double,
    val tiobe_functional: Double,
    val tiobe_security: Double,
    val tiobe_standard: Double
)

@Serializable
data class Metrics(
    val comment_ratio: Double,
    val cyclomatic_complexity: Int,
    val fanout_external: Int,
    val fanout_internal: Int,
    val halstead_bugprop: Double,
    val halstead_difficulty: Double,
    val halstead_effort: Double,
    val halstead_timerequired: Double,
    val halstead_volume: Double,
    val loc: Int,
    val maintainability_index: Double,
    val operands_sum: Int,
    val operands_uniq: Int,
    val operators_sum: Int,
    val operators_uniq: Int,
    val pylint: Double,
    val tiobe: Double,
    val tiobe_compiler: Double,
    val tiobe_complexity: Double,
    val tiobe_coverage: Double,
    val tiobe_duplication: Double,
    val tiobe_fanout: Double,
    val tiobe_functional: Double,
    val tiobe_security: Double,
    val tiobe_standard: Double
)

@Serializable
data class Stats(
    val max: Metrics,
    val mean: Metrics,
    val median: Metrics,
    val min: Metrics,
)