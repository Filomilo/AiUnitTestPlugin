package org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Jacoco

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import kotlinx.serialization.Serializable

@Serializable
data class Sessioninfo(

    @JacksonXmlProperty(isAttribute = true, localName = "id")
    val id: String,

    @JacksonXmlProperty(isAttribute = true, localName = "start")
    val start: Long,

    @JacksonXmlProperty(isAttribute = true, localName = "dump")
    val dump: Long
)
