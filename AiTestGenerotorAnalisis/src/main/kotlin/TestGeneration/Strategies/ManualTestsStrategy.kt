package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies

import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.filomilo.AiTestGenerator.Tools.PathObject
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRun
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import kotlin.time.Duration

class ManualTestsStrategy : TestGenerationStrategy {

    var logs: String=""

    override fun getNameIdentifier(): String {
        return "Manual_Tests"
    }

    override fun getDescription(): String {
        return "Manual test made already in project"
    }

    override fun runTestGenerationStrategy(llmProcessor: LLMProcessor, project: Project): AnalysisRun {
        logs = project.runTests()
        var report: TestReport = project.getReport()
        return AnalysisRun(
            llmModel = "none",
            project = project.name,
            strategyName = this.getNameIdentifier(),
            strategyDescription = this.getDescription(),
            report = report,
            deviceSpecification = llmProcessor.getDeviceSpecification(),
            duration = Duration.ZERO,
            executionLogs = listOf<String>(logs),
            promptResults = HashSet<LLMResponse>(),
            failureReason = null,
        )
    }

    override fun getWarnings(): Collection<Exception> {
        return emptyList()
    }

    override fun getPromptResults(): HashSet<LLMResponse>? {
       return null
    }


    override fun clearBuffers() {
    logs=""
    }

    override fun getGeneratedFiles(): List<PathObject>? {
        return emptyList()
    }

    override fun getExecutionLogs(): List<String>? {
      return listOf(logs)
    }

}