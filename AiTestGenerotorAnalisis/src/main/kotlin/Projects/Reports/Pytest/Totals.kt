package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import kotlinx.serialization.Serializable

import com.fasterxml.jackson.annotation.JsonProperty


@Serializable
data class Totals(
    @JsonProperty("covered_lines") var coveredLines: Int,
    @JsonProperty("num_statements") var numStatements: Int,
    @JsonProperty("percent_covered") var percentCovered: Double,
    @JsonProperty("percent_covered_display") var percentCoveredDisplay: String,
    @JsonProperty("missing_lines") var missingLines: Int,
    @JsonProperty("excluded_lines") var excludedLines: Int

)