package Tools.CodeMetric

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MetricsTest {
    private val jsonParser = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    @Test
    fun `test deserialize and serialize JSON`() {
        val jsonInput = """
            {
              "files": {
                "Calculator.py": {
                  "comment_ratio": 0.0,
                  "cyclomatic_complexity": 0,
                  "fanout_external": 0,
                  "fanout_internal": 0,
                  "halstead_bugprop": 0.072,
                  "halstead_difficulty": 29.4,
                  "halstead_effort": 6369.343,
                  "halstead_timerequired": 353.852,
                  "halstead_volume": 216.644,
                  "lang": ["Python"],
                  "loc": 11,
                  "maintainability_index": 104.187,
                  "operands_sum": 21,
                  "operands_uniq": 5,
                  "operators_sum": 30,
                  "operators_uniq": 14,
                  "pylint": 100.0,
                  "tiobe": 99.769,
                  "tiobe_compiler": 100.0,
                  "tiobe_complexity": 98.462,
                  "tiobe_coverage": 100.0,
                  "tiobe_duplication": 100.0,
                  "tiobe_fanout": 100.0,
                  "tiobe_functional": 100.0,
                  "tiobe_security": 100.0,
                  "tiobe_standard": 100.0
                }
              },
              "overall": {
                "comment_ratio": 0.0,
                "cyclomatic_complexity": 0,
                "fanout_external": 2,
                "fanout_internal": 0,
                "halstead_bugprop": 0.215,
                "halstead_difficulty": 26.667,
                "halstead_effort": 17213.202,
                "halstead_timerequired": 956.289,
                "halstead_volume": 645.495,
                "loc": 22,
                "maintainability_index": 87.281,
                "operands_sum": 48,
                "operands_uniq": 18,
                "operators_sum": 75,
                "operators_uniq": 20,
                "pylint": 100.0,
                "tiobe": 99.769,
                "tiobe_compiler": 100.0,
                "tiobe_complexity": 98.462,
                "tiobe_coverage": 100.0,
                "tiobe_duplication": 100.0,
                "tiobe_fanout": 100.0,
                "tiobe_functional": 100.0,
                "tiobe_security": 100.0,
                "tiobe_standard": 100.0
              },
              "stats": {
                "max": {
                  "comment_ratio": 0.0,
                  "cyclomatic_complexity": 0,
                  "fanout_external": 2,
                  "fanout_internal": 0,
                  "halstead_bugprop": 0.111,
                  "halstead_difficulty": 29.4,
                  "halstead_effort": 6369.343,
                  "halstead_timerequired": 353.852,
                  "halstead_volume": 334.358,
                  "loc": 11,
                  "maintainability_index": 104.187,
                  "operands_sum": 27,
                  "operands_uniq": 13,
                  "operators_sum": 45,
                  "operators_uniq": 14,
                  "pylint": 100.0,
                  "tiobe": 99.769,
                  "tiobe_compiler": 100.0,
                  "tiobe_complexity": 98.462,
                  "tiobe_coverage": 100.0,
                  "tiobe_duplication": 100.0,
                  "tiobe_fanout": 100.0,
                  "tiobe_functional": 100.0,
                  "tiobe_security": 100.0,
                  "tiobe_standard": 100.0
                },
                "mean": { 
                  "comment_ratio": 0.0,
                  "cyclomatic_complexity": 0,
                  "fanout_external": 1,
                  "fanout_internal": 0,
                  "halstead_bugprop": 0.092,
                  "halstead_difficulty": 20.931,
                  "halstead_effort": 5267.977,
                  "halstead_timerequired": 292.665,
                  "halstead_volume": 275.501,
                  "loc": 11,
                  "maintainability_index": 103.059,
                  "operands_sum": 24,
                  "operands_uniq": 9,
                  "operators_sum": 37,
                  "operators_uniq": 13,
                  "pylint": 100.0,
                  "tiobe": 99.769,
                  "tiobe_compiler": 100.0,
                  "tiobe_complexity": 98.462,
                  "tiobe_coverage": 100.0,
                  "tiobe_duplication": 100.0,
                  "tiobe_fanout": 100.0,
                  "tiobe_functional": 100.0,
                  "tiobe_security": 100.0,
                  "tiobe_standard": 100.0
                },
                "median": {
                  "comment_ratio": 0.0,
                  "cyclomatic_complexity": 0,
                  "fanout_external": 1,
                  "fanout_internal": 0,
                  "halstead_bugprop": 0.092,
                  "halstead_difficulty": 20.931,
                  "halstead_effort": 5267.977,
                  "halstead_timerequired": 292.665,
                  "halstead_volume": 275.501,
                  "loc": 11,
                  "maintainability_index": 103.059,
                  "operands_sum": 24,
                  "operands_uniq": 9,
                  "operators_sum": 37,
                  "operators_uniq": 13,
                  "pylint": 100.0,
                  "tiobe": 99.769,
                  "tiobe_compiler": 100.0,
                  "tiobe_complexity": 98.462,
                  "tiobe_coverage": 100.0,
                  "tiobe_duplication": 100.0,
                  "tiobe_fanout": 100.0,
                  "tiobe_functional": 100.0,
                  "tiobe_security": 100.0,
                  "tiobe_standard": 100.0
                },
                "min": { 
                  "comment_ratio": 0.0,
                  "cyclomatic_complexity": 0,
                  "fanout_external": 0,
                  "fanout_internal": 0,
                  "halstead_bugprop": 0.072,
                  "halstead_difficulty": 12.462,
                  "halstead_effort": 4166.611,
                  "halstead_timerequired": 231.478,
                  "halstead_volume": 216.644,
                  "loc": 11,
                  "maintainability_index": 101.931,
                  "operands_sum": 21,
                  "operands_uniq": 5,
                  "operators_sum": 30,
                  "operators_uniq": 12,
                  "pylint": 100.0,
                  "tiobe": 99.769,
                  "tiobe_compiler": 100.0,
                  "tiobe_complexity": 98.462,
                  "tiobe_coverage": 100.0,
                  "tiobe_duplication": 100.0,
                  "tiobe_fanout": 100.0,
                  "tiobe_functional": 100.0,
                  "tiobe_security": 100.0,
                  "tiobe_standard": 100.0
                },
                "sd": { 
                  "comment_ratio": 0.0,
                  "cyclomatic_complexity": 0,
                  "fanout_external": 1,
                  "fanout_internal": 0,
                  "halstead_bugprop": 0.028,
                  "halstead_difficulty": 11.977,
                  "halstead_effort": 1557.567,
                  "halstead_timerequired": 86.531,
                  "halstead_volume": 83.236,
                  "loc": 0,
                  "maintainability_index": 1.596,
                  "operands_sum": 4,
                  "operands_uniq": 5,
                  "operators_sum": 10,
                  "operators_uniq": 1,
                  "pylint": 0.0,
                  "tiobe": 0.0,
                  "tiobe_compiler": 0.0,
                  "tiobe_complexity": 0.0,
                  "tiobe_coverage": 0.0,
                  "tiobe_duplication": 0.0,
                  "tiobe_fanout": 0.0,
                  "tiobe_functional": 0.0,
                  "tiobe_security": 0.0,
                  "tiobe_standard": 0.0
                }
              }
            }
        """.trimIndent()

        val analysis = jsonParser.decodeFromString<ProjectMetricsReportMutlimetric>(jsonInput)
        val serialized = jsonParser.encodeToString(analysis)

        val roundTrip = jsonParser.decodeFromString<ProjectMetricsReportMutlimetric>(serialized)

        assertEquals(analysis, roundTrip, "Serialization round-trip should preserve data")
    }
}