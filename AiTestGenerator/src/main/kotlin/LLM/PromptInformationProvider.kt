package LLM

interface PromptInformationProvider {
    fun getTestingFramework():String?
    fun getFunctions(): String?
    fun getClasses(): String?
    fun getFiles(): String?

}