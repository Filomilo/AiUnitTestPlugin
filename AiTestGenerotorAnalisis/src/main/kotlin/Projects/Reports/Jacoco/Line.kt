package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import kotlinx.serialization.Serializable

@Serializable

data class Line(
    @JacksonXmlProperty(isAttribute = true, localName = "nr")
    val nr: Long,
    @JacksonXmlProperty(isAttribute = true, localName = "mi")
    val mi: Long,
    @JacksonXmlProperty(isAttribute = true, localName = "ci")
    val ci: Long,
    @JacksonXmlProperty(isAttribute = true, localName = "mb")
    val mb: Long,
    @JacksonXmlProperty(isAttribute = true, localName = "cb")
    val cb: Long
)
