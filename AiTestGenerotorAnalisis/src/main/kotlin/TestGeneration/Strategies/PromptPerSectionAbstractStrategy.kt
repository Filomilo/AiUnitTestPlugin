package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies

import LLM.CodeRetrivalExcpetion
import LLM.LlmParser
import LLM.PromptInformationProvider
import LLM.TestGenerationException
import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeParser
import Tools.CodeParsers.ParsingException
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerator.Tools.PathObject
import org.filomilo.AiTestGenerator.Tools.StringTools
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRun
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.DataPromptInformationProvider
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.PromptFormatter
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.text.replace

abstract class PromptPerSectionAbstractStrategy(prompt: String) : TestGenerationStrategy {

    companion object {
        val log = LoggerFactory.getLogger(PromptPerSectionAbstractStrategy.javaClass)
    }

    val promptBase: String = prompt
  private  var exceptions: MutableList<Exception> = mutableListOf()
  private  var promptResults: HashSet<LLMResponse> = HashSet<LLMResponse>()
  private  var generatedFiles: MutableList<PathObject> = mutableListOf()
  private  var executionLogs: MutableList<String> = mutableListOf()

    override fun getWarnings(): Collection<Exception> {
        return exceptions
    }

    override fun clearBuffers() {
        exceptions = mutableListOf()
        promptResults= HashSet<LLMResponse>()
        generatedFiles=mutableListOf()
        executionLogs=mutableListOf()
    }

    fun generateTestFiles(tests: Collection<CodeFile>, project: Project) {
        tests.forEach { x ->
            x.file = project.reolvePathToTestCodeFile(x).toFile()
            x.file?.createNewFile()
            x.file!!.writeText(x.getContent())
            generatedFiles.add(PathObject(
                name = x.file!!.name, content = x.getContent(),
                children = mutableListOf()
            ))
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

    abstract fun  GetPromptInformationProviderFromCodeSection(code: Code,project: Project): PromptInformationProvider

    fun generateTestsForSection(
        section: Code, llmProcessor: LLMProcessor, project: Project,

    ): Collection<CodeFile> {
        val prompt: String = PromptFormatter.resolveArguments(
            this.promptBase,
            GetPromptInformationProviderFromCodeSection(section,project)

        )
        var llmreponse: LLMResponse = llmProcessor.executePrompt(
            prompt
        )
        var response: LLMResponse = llmreponse
        var promptResult: String = response.response

        promptResults.add(response)
        val codeFilesFromResult: Collection<CodeFile> = getCodeFilesFromLlmResult(promptResult, project)
        codeFilesFromResult.forEach { x -> x.file = File("test_${section.code!!.replace(" ", "")}") }
        if (codeFilesFromResult.isEmpty()) {
            throw CodeRetrivalExcpetion("Couldn't extract any code file from llm result: \n[[\n $promptResult \n]]\n frotm project [[$project]]")
        }

        return codeFilesFromResult
    }

    fun generateTestsForSections(
        method: Collection<Code>,
        llmProcessor: LLMProcessor,
        project: Project
    ): Collection<CodeFile> {
        var tests: MutableCollection<CodeFile> = mutableListOf()
        for (code in method) {
            try {
                tests.addAll(generateTestsForSection(code, llmProcessor, project))
            } catch (ex: CodeRetrivalExcpetion) {
                exceptions.add(ex)
                PromptPerMethodStrategy.Companion.log.warn("Failed to genereatet test for method [[${code.getContent(project.codeParser.getCodeSeparator())}]] :: ${ex.message} :: ${ex.stackTrace} ")
            }
        }
        if (tests.isEmpty()) {

            throw TestGenerationException("no code generated for tests on project $project using $llmProcessor")
        }
        return tests

    }

    abstract fun getCodeSections(project: Project):Collection<Code>


    override fun runTestGenerationStrategy(llmProcessor: LLMProcessor, project: Project): AnalysisRun {
        project.clearTests()
        val sections:Collection<Code> =getCodeSections(project)

        val tests: Collection<CodeFile> = this.generateTestsForSections(sections, llmProcessor, project )
        generateTestFiles(tests, project)
        val logs: String = project.runTests()
        executionLogs.add(logs)
        val report: TestReport = project.getReport()

        return AnalysisRun(
            llmModel = llmProcessor.toString(),
            project = project.name,
            strategy= this,
            report = report,
            deviceSpecification = llmProcessor.getDeviceSpecification(),
            executionLogs = listOf(logs),
            promptResults = promptResults,
            generatedFiles = FilesManagment.getFolderContent(
                path = project.getTestsPath(),
                ignoredPaths = project.ignoredPaths.map { x -> project.ProjectPath.resolve(x) }.toList()
            ),
            failureReason = null
        )
    }



    override fun getPromptResults(): HashSet<LLMResponse>? {
        return this.promptResults
    }

    override fun getGeneratedFiles(): List<PathObject>? {
        return generatedFiles
    }

    override fun getExecutionLogs(): List<String>? {
       return executionLogs
    }



}