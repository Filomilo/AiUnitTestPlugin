package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy

import com.fasterxml.jackson.annotation.JsonProperty
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRun
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRunSuccess
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Tools.PathResolver
import java.nio.file.Path

interface TestGenerationStrategy
{

    companion object{

        @JvmStatic
         fun cloneProjectToTmpPath(project: Project): Project{
            val tmpPath: Path = PathResolver.ensureTmpDirectory().toAbsolutePath()
            val projectcloned = project.clone(tmpPath)
            return projectcloned;
        }
    }

@JsonProperty("Name")
fun getNameIdentifier(): String
@JsonProperty("Description")
fun getDescription(): String
fun runTestGenerationStrategy(llmProcessor: LLMProcessor, project: Project): AnalysisRunSuccess
}

