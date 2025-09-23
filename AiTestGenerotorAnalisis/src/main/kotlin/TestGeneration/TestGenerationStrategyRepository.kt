package org.filomilo.AiTestGenerotorAnalisis.TestGeneration

import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies.ManualTestsStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies.PromptPerFileStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies.PromptPerMethodStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy

object TestGenerationStrategyRepository {
    val strategies: Collection<TestGenerationStrategy> = mutableListOf(
        ManualTestsStrategy(),
        PromptPerMethodStrategy(
            """
                Create ##framework## tests for function ##functions##
            """.trimIndent(),listOf(
                TestGenerationStrategy.Tags.PER_METHOD)
        ),
        PromptPerMethodStrategy(
            """
                Create ##framework## tests for function 
                
                ##functions## 
                
                with project structure 
                
                ##tree##
            """.trimIndent(),listOf(
                TestGenerationStrategy.Tags.PER_METHOD, TestGenerationStrategy.Tags.WITH_PROJECT_TREE
            )
        ),
        PromptPerFileStrategy(
            """
                Create ##framework## tests for file ##filename## ::
                 ##filecontent##
            """.trimIndent(),listOf(
                TestGenerationStrategy.Tags.PER_FILE
            )),
        PromptPerFileStrategy(
            """
                Create ##framework## tests for file ##filename## ::
                 ##filecontent##
                  
                  with project structure 
                  ##tree##
            """.trimIndent(),listOf(
                TestGenerationStrategy.Tags.PER_FILE, TestGenerationStrategy.Tags.WITH_PROJECT_TREE
            )
        ),
        PromptPerFileStrategy(
            """
                Create ##framework## tests for file ##filename## ::
                 ##filecontent##
                  
                  with project structure 
                  ##tree##
                  
                  do not redefine code but import
            """.trimIndent(),listOf(
                TestGenerationStrategy.Tags.PER_FILE, TestGenerationStrategy.Tags.WITH_PROJECT_TREE, TestGenerationStrategy.Tags.IMPORT_SPECIFICATION
            )
        ),
        PromptPerFileStrategy(
            """
                Create ##framework## tests for file ##filename## ::
                 ##filecontent##

                  do not redefine code but import
            """.trimIndent(),listOf(
                TestGenerationStrategy.Tags.PER_FILE, TestGenerationStrategy.Tags.IMPORT_SPECIFICATION
            )
        )
    )
}