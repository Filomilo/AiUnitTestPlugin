package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Counter (
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    val type: Type,
    @JacksonXmlProperty(isAttribute = true, localName = "missed")
    val missed: Long,
    @JacksonXmlProperty(isAttribute = true, localName = "covered")
    val covered: Long
)
