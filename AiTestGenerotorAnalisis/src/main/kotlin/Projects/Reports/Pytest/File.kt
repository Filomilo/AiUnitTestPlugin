package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import kotlinx.serialization.Serializable

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@Serializable
data class File(

    @JsonProperty("executed_lines" ) var executedLines : ArrayList<Int>,
    @JsonProperty("summary"        ) var summary       : Summary,
    @JsonProperty("missing_lines"  ) var missingLines  : ArrayList<Int>,
    @JsonProperty("excluded_lines" ) var excludedLines : ArrayList<Int>,
    @JsonProperty("functions"      ) var functions     : Map<String,Function>,
    @JsonProperty("classes"        ) var classes       : Map<String, Class>


)
