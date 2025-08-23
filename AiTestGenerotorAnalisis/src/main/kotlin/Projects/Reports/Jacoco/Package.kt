package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import kotlinx.serialization.Serializable

@Serializable
data class Package (
    @JacksonXmlProperty(localName = "class")
    @field:JsonProperty("package")
    val packageClass: Class,
    @JacksonXmlProperty(localName = "sourcefile")
    val sourcefile: Sourcefile,
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "counter")
    val counter: List<Counter>,
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    val name: String
)
