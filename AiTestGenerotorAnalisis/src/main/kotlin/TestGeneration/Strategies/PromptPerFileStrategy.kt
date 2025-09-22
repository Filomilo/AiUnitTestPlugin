package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies

import LLM.PromptInformationProvider
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerator.Tools.PathObject
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRun
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.DataPromptInformationProvider
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import org.slf4j.LoggerFactory
import java.nio.file.Files

class PromptPerFileStrategy(prompt: String) : PromptPerSectionAbstractStrategy(prompt) {


    companion object {
        val log = LoggerFactory.getLogger(PromptPerMethodStrategy.javaClass)
    }



    override fun getNameIdentifier(): String {
        return "Prompt_per_file"
    }

    override fun getDescription(): String {
        return "Prompt per file for test generation formatted from :: \n[[ $promptBase ]\n]]\n"
    }


    override fun GetPromptInformationProviderFromCodeSection(
        code: Code,
        project: Project
    ): PromptInformationProvider {
        return DataPromptInformationProvider(
            code.getContent(project.codeParser.getCodeSeparator()),
            project.testingFramework,
            fileContent = code.children[0].getContent(project.codeParser.getCodeSeparator()),
            _projectTree = FilesManagment.getFolderContent(project.ProjectPath, project.ignoredPaths),
            fileName = code.code,
        )
    }

    override fun getCodeSections(project: Project): Collection<Code> {
        return project.getAllParsedFiles().map { x-> Code(x.file!!.name, x.codes) }

    }


}