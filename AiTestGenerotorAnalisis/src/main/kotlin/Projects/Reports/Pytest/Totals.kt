package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import kotlinx.serialization.Serializable

import com.fasterxml.jackson.annotation.JsonProperty


@Serializable
data class Totals (

  @JsonProperty("covered_lines"           ) var coveredLines          : Int?    = null,
  @JsonProperty("num_statements"          ) var numStatements         : Int?    = null,
  @JsonProperty("percent_covered"         ) var percentCovered        : Double? = null,
  @JsonProperty("percent_covered_display" ) var percentCoveredDisplay : String? = null,
  @JsonProperty("missing_lines"           ) var missingLines          : Int?    = null,
  @JsonProperty("excluded_lines"          ) var excludedLines         : Int?    = null

)