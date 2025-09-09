package org.filomilo.AiTestGenerotorAnalisis

import Exceptions.LlmProcessingException
import LLM.Apis.Ollama.OllamaApiGenerator
import org.filomilo.AiTestGenerator.LLM.Apis.ApiConnectionFactory
import org.filomilo.AiTestGenerator.LLM.Containers.ContainersManager
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LlmRepository
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectsRepository
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.TestGenerationStrategyRepository
import org.filomilo.AiTestGenerotorAnalisis.Tools.PathResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object AnalysisRunner {
    private val log: Logger = LoggerFactory.getLogger(AnalysisRunner::class.java)

    lateinit var llmRepository: LlmRepository;
    lateinit var containerManager: ContainersManager
    var analysisResults: AnalysisResults = AnalysisResults()

    init {
        this.containerManager = DockerConnection

        this.llmRepository = LlmRepository(
            containerManager,
            ApiConnectionFactory.getApiConnector(),
            OllamaApi = OllamaApiGenerator.getOllamaApi()
        )
        this.llmRepository!!.initlize();

    }

    fun runStrategyOnLLMProcessorOnProejct(
        llmProcessor: LLMProcessor,
        strategy: TestGenerationStrategy,
        project: Project
    ) {
        log.info("runStrategyOnLLMProcessorOnProejct:: [[${project.name}]]")
        val clonedProject: Project = project.clone(PathResolver.resolveTmpFolder(project.name))

        try {
            var AnalysisRun: AnalysisRunSuccess = strategy.runTestGenerationStrategy(llmProcessor, clonedProject)
            this.analysisResults.addRun(AnalysisRun)
        } catch (ex: LlmProcessingException) {
            this.analysisResults.addRunFailure(
                AnalysisRunFailure(
                    failureReason = ex,
                    llmModel = llmProcessor.getName(),
                    project = project.name,
                    strategy = strategy.getNameIdentifier(),

                    )
            )


        } finally {
            clonedProject.destroy()
        }


    }

    fun runStrategyOnLLMProcessor(llmProcessor: LLMProcessor, strategy: TestGenerationStrategy) {
        log.info("runStrategyOnLLMProcessor:: [[${strategy.getNameIdentifier()}]]")
        for (project: Project in ProjectsRepository.projects) {
            runStrategyOnLLMProcessorOnProejct(llmProcessor, strategy, project)
        }
    }

    fun runAnalysisOnLLMProcessor(llmProcessor: LLMProcessor) {
        llmProcessor.load()
        log.info("runAnalysisOnLLMProcessor:: [[$llmProcessor]]")
        for (strategy: TestGenerationStrategy in TestGenerationStrategyRepository.strategies) {
            runStrategyOnLLMProcessor(llmProcessor, strategy)
        }
        llmProcessor.unload()
    }

    fun runAnalysis() {
        log.info("Runnign analysis")
        this.analysisResults = AnalysisResults.load()
        for (llmProcessor: LLMProcessor in this.llmRepository.ListOfLlmProcessors) {
            runAnalysisOnLLMProcessor(llmProcessor)
        }
        this.analysisResults.save()
    }
}