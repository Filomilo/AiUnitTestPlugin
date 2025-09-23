package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies

import Exceptions.LlmProcessingException
import LLM.CodeRetrivalExcpetion
import LLM.LlmParser
import LLM.PromptInformationProvider
import LLM.TestGenerationException
import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeParser
import Tools.CodeParsers.ParsingException
import Tools.PathResolver
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerator.Tools.PathObject
import org.filomilo.AiTestGenerator.Tools.StringTools
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRun
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.Pytest.File
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.DataPromptInformationProvider
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.PromptFormatter
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import org.slf4j.LoggerFactory
import java.nio.file.Path
import java.util.Dictionary


class PromptPerMethodStrategy(prompt: String,tags: List<TestGenerationStrategy.Tags>) : PromptPerSectionAbstractStrategy(prompt) {

    companion object {
        val log = LoggerFactory.getLogger(PromptPerMethodStrategy.javaClass)
    }
    private val tags: List<TestGenerationStrategy.Tags> = tags


    override fun getNameIdentifier(): String {
        return "Prompt_per_method"
    }

    override fun getDescription(): String {
        return "Prompt per method for test generation formatted from :: \n[[ $promptBase ]\n]]\n"
    }

    override fun getTags(): List<TestGenerationStrategy.Tags> {
    return tags
    }

    override fun GetPromptInformationProviderFromCodeSection(
        code: Code,
        project: Project
    ): PromptInformationProvider {
     return DataPromptInformationProvider(
         code.getContent(project.codeParser.getCodeSeparator()),
         project.testingFramework,
         fileContent = null,
         _projectTree = FilesManagment.getFolderContent(project.ProjectPath, project.ignoredPaths),
         fileName = null,
     )
    }

    override fun getCodeSections(project: Project): Collection<Code> {
        return project.getAllMethodsWithParents()
    }


}



