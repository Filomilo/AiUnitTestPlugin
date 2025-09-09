package org.filomilo.AiTestGenerotorAnalisis.TestGeneration

import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies.ManualTestsStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies.PromptPerMethodStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy

object TestGenerationStrategyRepository {
    val strategies: Collection<TestGenerationStrategy> = mutableListOf(
        ManualTestsStrategy(),
        PromptPerMethodStrategy(
            """
                Create ##framework## for function ##functions##
            """.trimIndent()
        )
    )
}