package org.filomilo.AiTestGenerotorAnalisis

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

object AnalysisRunner {

    lateinit var llmRepository: LlmRepository;
    lateinit  var containerManager: ContainersManager
    lateinit  var analysisResults: AnalysisResults
    init {
        this. containerManager = DockerConnection

        this.llmRepository = LlmRepository(
            containerManager,
      ApiConnectionFactory.getApiConnector(),
            OllamaApi = OllamaApiGenerator.getOllamaApi()
        )
        this.llmRepository!!.initlize();

    }
    fun runStrategyOnLLMProcessorOnProejct(llmProcessor: LLMProcessor, strategy:TestGenerationStrategy,project: Project){
        var AnalysisRun: AnalysisRunSuccess=         strategy.runTestGenerationStrategy(llmProcessor,project)
        this.analysisResults.addRun(AnalysisRun)
    }
    fun runStrategyOnLLMProcessor(llmProcessor: LLMProcessor, strategy:TestGenerationStrategy){
        for(project: Project in ProjectsRepository.projects){
            runStrategyOnLLMProcessorOnProejct(llmProcessor,strategy,project)
        }
    }
    fun runAnalysisOnLLMProcessor(llmProcessor: LLMProcessor){
//        llmProcessor.load()
        for(strategy: TestGenerationStrategy in TestGenerationStrategyRepository.strategies){
            runStrategyOnLLMProcessor(llmProcessor,strategy)
        }
//        llmProcessor.unload()
    }

    fun runAnalysis() {
        this.analysisResults= AnalysisResults.load()
        for(llmProcessor: LLMProcessor in this.llmRepository.ListOfLlmProcessors){
            runAnalysisOnLLMProcessor(llmProcessor)
        }
        this.analysisResults.save()
    }
}