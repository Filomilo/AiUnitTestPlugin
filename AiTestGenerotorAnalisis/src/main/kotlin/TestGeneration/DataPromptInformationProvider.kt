package org.filomilo.AiTestGenerotorAnalisis.TestGeneration

import LLM.PromptInformationProvider
import org.filomilo.AiTestGenerator.Tools.PathObject

data class DataPromptInformationProvider (
private val method: String?,
private val framework: String?,
private val fileName: String?,
private val fileContent: String?,
private val _projectTree: List<PathObject>?,

) : PromptInformationProvider {
    override fun getTestingFramework(): String? {
        return framework
    }

    override fun getFunctions(): String? {
        return method
    }

    override fun getFileName(): String? {
        return fileName
    }

    override fun getFileContent(): String? {
  return fileContent
    }


    override fun getProjectTree(): List<PathObject>? {
        return _projectTree
    }


}