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
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.PromptFormatter
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import org.slf4j.LoggerFactory
import java.nio.file.Path
import java.util.Dictionary


class PromptPerMethodStrategy(prompt: String) : TestGenerationStrategy {

    companion object {
        val log = LoggerFactory.getLogger(PromptPerMethodStrategy.javaClass)
    }


    val promptBase: String = prompt
    var exceptions: MutableList<Exception> = mutableListOf()

    override fun getNameIdentifier(): String {
        return "Simple_Prompt_Strategy"
    }

    override fun getDescription(): String {
        return "Simple propmpt for test generation formatted from :: \n[[ $promptBase ]\n]]\n"
    }

    data class SingleMethodProvider(
        val method: String,
        val framework: String,
        val _projectTree: List<PathObject>
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

        override fun getProjectTree(): List<PathObject> {
            return _projectTree
        }


    }

    fun getCodeFilesFromLlmResult(promptResult: String, project: Project): Collection<CodeFile> {
        var codeFiles: MutableList<CodeFile> = emptyList<CodeFile>().toMutableList()
        var codeParser: CodeParser = project.codeParser
        var listings: Collection<String> =
            LlmParser.extractListingFromLlmResponse(promptResult, project.getLanguage())
                .map { x -> StringTools.turnCharsIntoEscapeSequance(x) }.toList()
        for (listing in listings) {
            try {
                val codeFile: CodeFile = codeParser.parseContent(listing)
                codeFiles.add(codeFile)
            } catch (ex: ParsingException) {
                exceptions.add(ex)
                log.warn("coudnt parse this listing \n[[\n$listing\n]]\n with parser [[$codeParser]]")
            }
        }

        return codeFiles;
    }

    fun generateTestsForMethod(
        method: Code, llmProcessor: LLMProcessor, project: Project,
        promptResults: HashSet<LLMResponse>
    ): Collection<CodeFile> {
        val prompt: String = PromptFormatter.resolveArguments(
            this.promptBase,
            SingleMethodProvider(
                method.getContent(project.codeParser.getCodeSeparator()),
                project.testingFramework,
                FilesManagment.getFolderContent(project.ProjectPath, project.ignoredPaths)
            )
        )
        var llmreponse: LLMResponse = llmProcessor.executePrompt(
            prompt
        )
        var response: LLMResponse = llmreponse
        var promptResult: String = response.response

        promptResults.add(response)
        val codeFilesFromResult: Collection<CodeFile> = getCodeFilesFromLlmResult(promptResult, project)
        codeFilesFromResult.forEach { x -> x.file = java.io.File("test_${method.code!!.replace(" ", "")}") }
        if (codeFilesFromResult.isEmpty()) {
            throw CodeRetrivalExcpetion("Couldn't extract any code file from llm result: \n[[\n $promptResult \n]]\n frotm project [[$project]]")
        }

        return codeFilesFromResult
    }

    fun generateTestsForMethods(
        method: Collection<Code>,
        llmProcessor: LLMProcessor,
        project: Project,
        responses: HashSet<LLMResponse>
    ): Collection<CodeFile> {
        var tests: MutableCollection<CodeFile> = mutableListOf()
        for (code in method) {
            try {
                tests.addAll(generateTestsForMethod(code, llmProcessor, project, responses))
            } catch (ex: CodeRetrivalExcpetion) {
                exceptions.add(ex)
                log.warn("Failed to genereatet test for method [[${code.getContent(project.codeParser.getCodeSeparator())}]] :: ${ex.message} :: ${ex.stackTrace} ")
            }
        }
        if (tests.isEmpty()) {

            throw TestGenerationException("no code generated for tests on project $project using $llmProcessor")
        }
        return tests

    }

    fun generateTestFiles(tests: Collection<CodeFile>, project: Project) {
        tests.forEach { x ->
            x.file = project.reolvePathToTestCodeFile(x).toFile()
            x.file?.createNewFile()
            x.file!!.writeText(x.getContent())
        }

    }

    override fun runTestGenerationStrategy(llmProcessor: LLMProcessor, project: Project): AnalysisRun {
        project.clearTests()
        val methods = project.getAllMethodsWithParents()
        val promptResults: HashSet<LLMResponse> = HashSet<LLMResponse>()
        val tests: Collection<CodeFile> = this.generateTestsForMethods(methods, llmProcessor, project, promptResults)
        generateTestFiles(tests, project)
        val logs: String = project.runTests()
        val report: TestReport = project.getReport()

        return AnalysisRun(
            llmModel = llmProcessor.toString(),
            project = project.name,
            strategy = getNameIdentifier(),
            strategyDescription = getDescription(),
            report = report,
            deviceSpecification = llmProcessor.getDeviceSpecification(),
            executionLogs = listOf(logs),
            promptResults = promptResults,
            generatedFiles = FilesManagment.getFolderContent(
                path = project.getTestsPath(),
                ignoredPaths = project.ignoredFiles.map { x -> project.ProjectPath.resolve(x) }.toList()
            ),
            failureReason = null
        )
    }

    override fun getWarnings(): Collection<Exception> {
        return exceptions
    }

    override fun clearBuffers() {
        exceptions = mutableListOf()
    }


}