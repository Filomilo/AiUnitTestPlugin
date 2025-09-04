package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies

import LLM.PromptInformationProvider
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRunSuccess
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.PromptFormatter
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy

class PromptPerMethodStrategy(prompt: String) : TestGenerationStrategy {

    val promptBase: String = prompt


    override fun getNameIdentifier(): String {
        return "Simple_Prompt_Strategy"
    }

    override fun getDescription(): String {
        return "Simple propmpt for test generation formatted from :: \n[[ $promptBase ]\n]]\n"
    }

    data class SingleMethodProvider(
        val method: String,
        val framework: String
    ) : PromptInformationProvider {
        override fun getTestingFramework(): String? {
            return framework
        }

        override fun getFunctions(): String? {
            return method
        }

        override fun getClasses(): String? {
            return null
        }

        override fun getFiles(): String? {
            return null
        }

    }

    fun generateTestsForMethod(method: Code, llmProcessor: LLMProcessor, project: Project): Collection<Code> {

        var promptResult: String = llmProcessor.executePrompt(
            PromptFormatter.resolveArguments(
                this.promptBase,
                SingleMethodProvider(
                    method.getContent(project.codeParser.getCodeSeparator()),
                    project.testingFramework

                )
            )
        )
        var cddefile=
        TODO("not implemented")
    }

    fun generateTestsForMethods(
        method: Collection<Code>,
        llmProcessor: LLMProcessor,
        project: Project
    ): Collection<Project> {
        var tests: MutableCollection<Code> = mutableListOf()
        for (code in method) {
            tests.addAll(generateTestsForMethod(code, llmProcessor, project))
        }

        TODO("not implemented")
    }

    override fun runTestGenerationStrategy(llmProcessor: LLMProcessor, project: Project): AnalysisRunSuccess {
        val methods = project.getAllMethods()
        val tests = this.generateTestsForMethods(methods, llmProcessor, project)
        TODO("not implemented")
    }
}