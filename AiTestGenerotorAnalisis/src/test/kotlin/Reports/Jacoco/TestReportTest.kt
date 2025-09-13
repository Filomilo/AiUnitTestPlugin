package Reports.Jacoco

import com.fasterxml.jackson.databind.json.JsonMapper
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.Json
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.junit.jupiter.api.Test
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlinx.serialization.json.JsonEncoder
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReportSerializer
import kotlin.test.assertEquals

class TestReportTest {

    class exampleReport : TestReport {
        override fun getCoveragePercent(): Float {
            return 0.5f
        }


    }

    @Test
    fun seriableTest() {

        val json = Json.encodeToString(TestReportSerializer, exampleReport())
        assertEquals(
            """
            {"CoveragePercent":0.5,"TestGenerationTime":"PT10H","TestRunLogs":"sample"}
        """.trimIndent(), json
        )

    }


}