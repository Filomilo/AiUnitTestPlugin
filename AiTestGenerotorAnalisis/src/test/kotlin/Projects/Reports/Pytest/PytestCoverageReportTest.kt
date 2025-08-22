package Projects.Reports.Pytest

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco.JacocoReport
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest.PytestCoverageReport
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PytestCoverageReportTest {

    val reportStrng: String="""
        {
          "meta": {
            "format": 3,
            "version": "7.10.2",
            "timestamp": "2025-08-21T10:38:05.039751",
            "branch_coverage": false,
            "show_contexts": false
          },
          "files": {
            "calculator\\Calculator.py": {
              "executed_lines": [
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                11
              ],
              "summary": {
                "covered_lines": 10,
                "num_statements": 11,
                "percent_covered": 90.9090909090909,
                "percent_covered_display": "91",
                "missing_lines": 1,
                "excluded_lines": 0
              },
              "missing_lines": [
                10
              ],
              "excluded_lines": [],
              "functions": {
                "Calculator.add": {
                  "executed_lines": [
                    3
                  ],
                  "summary": {
                    "covered_lines": 1,
                    "num_statements": 1,
                    "percent_covered": 100.0,
                    "percent_covered_display": "100",
                    "missing_lines": 0,
                    "excluded_lines": 0
                  },
                  "missing_lines": [],
                  "excluded_lines": []
                },
                "Calculator.subtract": {
                  "executed_lines": [
                    5
                  ],
                  "summary": {
                    "covered_lines": 1,
                    "num_statements": 1,
                    "percent_covered": 100.0,
                    "percent_covered_display": "100",
                    "missing_lines": 0,
                    "excluded_lines": 0
                  },
                  "missing_lines": [],
                  "excluded_lines": []
                },
                "Calculator.multiply": {
                  "executed_lines": [
                    7
                  ],
                  "summary": {
                    "covered_lines": 1,
                    "num_statements": 1,
                    "percent_covered": 100.0,
                    "percent_covered_display": "100",
                    "missing_lines": 0,
                    "excluded_lines": 0
                  },
                  "missing_lines": [],
                  "excluded_lines": []
                },
                "Calculator.divide": {
                  "executed_lines": [
                    9,
                    11
                  ],
                  "summary": {
                    "covered_lines": 2,
                    "num_statements": 3,
                    "percent_covered": 66.66666666666667,
                    "percent_covered_display": "67",
                    "missing_lines": 1,
                    "excluded_lines": 0
                  },
                  "missing_lines": [
                    10
                  ],
                  "excluded_lines": []
                },
                "": {
                  "executed_lines": [
                    1,
                    2,
                    4,
                    6,
                    8
                  ],
                  "summary": {
                    "covered_lines": 5,
                    "num_statements": 5,
                    "percent_covered": 100.0,
                    "percent_covered_display": "100",
                    "missing_lines": 0,
                    "excluded_lines": 0
                  },
                  "missing_lines": [],
                  "excluded_lines": []
                }
              },
              "classes": {
                "Calculator": {
                  "executed_lines": [
                    3,
                    5,
                    7,
                    9,
                    11
                  ],
                  "summary": {
                    "covered_lines": 5,
                    "num_statements": 6,
                    "percent_covered": 83.33333333333333,
                    "percent_covered_display": "83",
                    "missing_lines": 1,
                    "excluded_lines": 0
                  },
                  "missing_lines": [
                    10
                  ],
                  "excluded_lines": []
                },
                "": {
                  "executed_lines": [
                    1,
                    2,
                    4,
                    6,
                    8
                  ],
                  "summary": {
                    "covered_lines": 5,
                    "num_statements": 5,
                    "percent_covered": 100.0,
                    "percent_covered_display": "100",
                    "missing_lines": 0,
                    "excluded_lines": 0
                  },
                  "missing_lines": [],
                  "excluded_lines": []
                }
              }
            },
            "calculator\\__init__.py": {
              "executed_lines": [
                0
              ],
              "summary": {
                "covered_lines": 0,
                "num_statements": 0,
                "percent_covered": 100.0,
                "percent_covered_display": "100",
                "missing_lines": 0,
                "excluded_lines": 0
              },
              "missing_lines": [],
              "excluded_lines": [],
              "functions": {
                "": {
                  "executed_lines": [],
                  "summary": {
                    "covered_lines": 0,
                    "num_statements": 0,
                    "percent_covered": 100.0,
                    "percent_covered_display": "100",
                    "missing_lines": 0,
                    "excluded_lines": 0
                  },
                  "missing_lines": [],
                  "excluded_lines": []
                }
              },
              "classes": {
                "": {
                  "executed_lines": [],
                  "summary": {
                    "covered_lines": 0,
                    "num_statements": 0,
                    "percent_covered": 100.0,
                    "percent_covered_display": "100",
                    "missing_lines": 0,
                    "excluded_lines": 0
                  },
                  "missing_lines": [],
                  "excluded_lines": []
                }
              }
            }
          },
          "totals": {
            "covered_lines": 10,
            "num_statements": 11,
            "percent_covered": 90.9090909090909,
            "percent_covered_display": "91",
            "missing_lines": 1,
            "excluded_lines": 0
          }
        }
    """.trimIndent()


    @Test
    fun PytestFromJson() {
        val mapper: JsonMapper = JsonMapper()
        mapper.registerModules()
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        val pytestCoverageReport: PytestCoverageReport =   mapper.readValue(reportStrng, PytestCoverageReport::class.java)
        assertEquals(3,pytestCoverageReport.meta.format)
      assertEquals(10,pytestCoverageReport.files.get("calculator\\Calculator.py")!!.summary.coveredLines)
    }
}