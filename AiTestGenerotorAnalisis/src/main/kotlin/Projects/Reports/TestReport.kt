package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports

import jdk.jfr.Percentage
import jdk.jfr.Timespan
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

import kotlin.time.Duration
import kotlin.time.Duration.Companion.parseIsoString

@Serializer(forClass = Duration::class)
object DurationIsoSerializer : KSerializer<Duration> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Duration", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Duration) {
        encoder.encodeString(value.toIsoString())
    }

    override fun deserialize(decoder: Decoder): Duration {
        return parseIsoString(decoder.decodeString())
    }
}

@Serializer(forClass = TestReport::class)
object TestReportSerializer : KSerializer<TestReport> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("TestReport") {
            element<Float>("CoveragePercent")
            element<Duration>("TestGenerationTime")
            element<Int>("TestRunLogs")
        }

    override fun serialize(encoder: Encoder, value: TestReport) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeFloatElement(descriptor, 0, value.getCoveragePercent())
        composite.encodeStringElement(descriptor, 1, value.getTestGenerationTime().toIsoString())
        composite.encodeStringElement(descriptor, 2, value.getTestRunLogs())
        composite.endStructure(descriptor)
    }

}

@Serializable(with = TestReportSerializer::class)
interface TestReport {
    fun getCoveragePercent(): Float

    fun getTestGenerationTime(): Duration
    fun getTestRunLogs(): String
}