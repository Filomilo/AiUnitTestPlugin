package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import kotlinx.serialization.Serializable

@Serializable
data class Method(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty( localName = "counter")
    val counter: List<Counter>,
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    val name: String,
    @JacksonXmlProperty(isAttribute = true, localName = "desc")
    val desc: String,
    @JacksonXmlProperty(isAttribute = true, localName = "line")
    val line: Long
)

