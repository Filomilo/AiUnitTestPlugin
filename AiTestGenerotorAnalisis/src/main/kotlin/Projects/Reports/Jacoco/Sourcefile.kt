package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Sourcefile(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "line")
    val line: List<Line>,
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "counter")
    val counter: List<Counter>,
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    val name: String
)
