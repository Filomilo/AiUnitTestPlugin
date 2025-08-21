package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import com.fasterxml.jackson.annotation.JsonCreator
import kotlinx.serialization.Serializable

import com.fasterxml.jackson.annotation.JsonProperty

@Serializable
data class PytestCoverageReport(

  @JsonProperty("meta"   ) var meta   : Meta,
  @JsonProperty("files"  ) var files  : Map<String,File>,
  @JsonProperty("totals" ) var totals : Totals

)