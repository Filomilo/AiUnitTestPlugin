package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy

import com.fasterxml.jackson.annotation.JsonProperty
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRun
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import Tools.PathResolver
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.filomilo.AiTestGenerator.Tools.PathObject
import java.nio.file.Path

interface TestGenerationStrategy {


    @JsonProperty("Name")
    fun getNameIdentifier(): String

    @JsonProperty("Description")
    fun getDescription(): String
    fun runTestGenerationStrategy(llmProcessor: LLMProcessor, project: Project): AnalysisRun
    fun getWarnings(): Collection<Exception>
    fun getPromptResults(): HashSet<LLMResponse>?
    fun clearBuffers()
    fun getGeneratedFiles(): List<PathObject>?
    fun getExecutionLogs(): List<String>?

}

