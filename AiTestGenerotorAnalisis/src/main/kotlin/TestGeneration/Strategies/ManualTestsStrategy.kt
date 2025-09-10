package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies

import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRun
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRunSuccess
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import kotlin.time.Duration

class ManualTestsStrategy : TestGenerationStrategy {
    override fun getNameIdentifier(): String {
        return "Manual_Tests"
    }

    override fun getDescription(): String {
        return "Manual test made already in project"
    }

    override fun runTestGenerationStrategy(llmProcessor: LLMProcessor, project: Project): AnalysisRunSuccess {
        project.runTests()
        var report: TestReport = project.getReport()
        return AnalysisRunSuccess(
            llmModel = "none",
            project = project.name,
            strategy = getNameIdentifier(),
            report = report,
            deviceSpecification = llmProcessor.getDeviceSpecification(),
            duration = Duration.ZERO
        )
    }

}