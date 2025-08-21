package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import kotlinx.serialization.Serializable

@Serializable
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
)

