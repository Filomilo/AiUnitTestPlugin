package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest

import com.fasterxml.jackson.annotation.JsonCreator
import kotlinx.serialization.Serializable

import com.fasterxml.jackson.annotation.JsonProperty
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import kotlin.time.Duration

@Serializable
data class PytestCoverageReport(

    @JsonProperty("meta") var meta: Meta,
    @JsonProperty("files") var files: Map<String, File>,
    @JsonProperty("totals") var totals: Totals

) : TestReport {
    override fun getCoveragePercent(): Float {
        TODO("Not yet implemented")
    }

    override fun getTestGenerationTime(): Duration {
        TODO("Not yet implemented")
    }

    override fun getTestRunLogs(): String {
        TODO("Not yet implemented")
    }
}