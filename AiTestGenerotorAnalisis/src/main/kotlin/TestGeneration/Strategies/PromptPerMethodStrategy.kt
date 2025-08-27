package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies

import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRunSuccess
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy

class PromptPerMethodStrategy(prompt:String): TestGenerationStrategy {

    val promptBase: String = prompt


    override fun getNameIdentifier(): String {
        return "Simple_Prompt_Strategy"
    }

    override fun getDescription(): String {
        return "Simple propmpt for test generation formatted from :: \n[[ $promptBase ]\n]]\n"
    }



    override fun runTestGenerationStrategy(llmProcessor: LLMProcessor, project: Project): AnalysisRunSuccess {
        val methods=project.getAllMehothods()
        TODO("not implemented")
    }
}