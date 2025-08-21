package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import kotlinx.serialization.Serializable

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime


@Serializable
data class Meta (

  @JsonProperty("format"          ) var format         : Int,
  @JsonProperty("version"         ) var version        : String,
  @JsonProperty("timestamp"       ) var timestamp      : String ,
  @JsonProperty("branch_coverage" ) var branchCoverage : Boolean,
  @JsonProperty("show_contexts"   ) var showContexts   : Boolean

)