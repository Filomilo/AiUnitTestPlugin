package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import kotlin.time.Duration

@Serializer(forClass = TestReport::class)
@JacksonXmlRootElement(localName = "report")
data class JacocoReport(

    @JacksonXmlProperty(localName = "sessioninfo")
    val sessioninfo: Sessioninfo,

    @JacksonXmlProperty(localName = "package")
    @field:JsonProperty("package")
    val reportPackage: Package,

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "counter")
    val counter: List<Counter>,
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    val name: String
) : TestReport {
    override fun getCoveragePercent(): Float {
        val counter: Counter = counter.filter { x -> x.type == Type.INSTRUCTION }.first()
        return (counter.covered.toFloat()) / (counter.covered + counter.missed)
    }


}

