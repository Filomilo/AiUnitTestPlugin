package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import kotlinx.serialization.Serializable

@Serializable
data class Class (
    @JacksonXmlProperty(localName = "method")
    @JacksonXmlElementWrapper(useWrapping = false)
    val method: List<Method>,
    @JacksonXmlProperty(localName = "counter")
    @JacksonXmlElementWrapper(useWrapping = false)
    val counter: List<Counter>,
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    val name: String,
    @JacksonXmlProperty(isAttribute = true, localName = "sourcefilename")
    val sourcefilename: String
)
