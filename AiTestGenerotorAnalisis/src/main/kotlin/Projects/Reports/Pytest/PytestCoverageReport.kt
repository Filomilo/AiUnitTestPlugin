package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import com.fasterxml.jackson.annotation.JsonCreator
import kotlinx.serialization.Serializable

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializer
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import kotlin.time.Duration

@Serializer(forClass = TestReport::class)
data class PytestCoverageReport(

    @JsonProperty("meta") var meta: Meta,
    @JsonProperty("files") var files: Map<String, File>,
    @JsonProperty("totals") var totals: Totals

) : TestReport {
    override fun getCoveragePercent(): Float {
        return totals.percentCovered.toFloat() / 100
    }


}