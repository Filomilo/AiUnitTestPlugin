package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import kotlinx.serialization.Serializable

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@Serializable
data class Class(
    @JsonProperty("executed_lines" ) var executedLines : ArrayList<Int>,
    @JsonProperty("summary"        ) var summary       : Summary,
    @JsonProperty("missing_lines"  ) var missingLines  : ArrayList<Int>,
    @JsonProperty("excluded_lines" ) var excludedLines : ArrayList<String>

) {
}