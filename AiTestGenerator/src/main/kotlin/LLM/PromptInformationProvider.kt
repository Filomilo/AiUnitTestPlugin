package LLM

import org.filomilo.AiTestGenerator.Tools.PathObject

interface PromptInformationProvider {
    fun getTestingFramework(): String?
    fun getFunctions(): String?
    fun getFileName(): String?
    fun getFileContent(): String?
    fun getProjectTree(): List<PathObject>?

}