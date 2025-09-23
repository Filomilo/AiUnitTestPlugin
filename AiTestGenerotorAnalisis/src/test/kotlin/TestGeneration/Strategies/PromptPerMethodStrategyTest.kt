package TestGeneration.Strategies

import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies.PromptPerMethodStrategy
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PromptPerMethodStrategyTest {
    @Test
    fun generateTestFiles() {
    }

    @Test
    fun runTestGenerationStrategy() {
        var promptPerMethodStrategy: PromptPerMethodStrategy = PromptPerMethodStrategy(
            """
                Create ##framework## for function 
                
                ##functions##
            """.trimIndent(),
            tags = emptyList()
        )


    }
}