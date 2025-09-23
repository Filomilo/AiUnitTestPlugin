import kotlinx.serialization.json.Json
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategies.PromptPerMethodStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy.Tags
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AnalysisRunTest {
    
    
    val exampleStrategy : PromptPerMethodStrategy= PromptPerMethodStrategy(
        prompt = "Test Test",
        tags = listOf(Tags.DUMMY)
    )
    
    @Test
    fun equals() {

        val expectedJson: String="""
            {"name":"Prompt_per_method","description":"Prompt per method for test generation formatted from :: \n[[ Test Test ]\n]]\n","tags":["DUMMY"]}
        """.trimIndent()

        val serilazedJson: String= Json.encodeToString(exampleStrategy as TestGenerationStrategy)

        assertEquals(expectedJson,serilazedJson)

        val desirlziedStrategy: TestGenerationStrategy= Json.decodeFromString<TestGenerationStrategy>(serilazedJson)
        assertEquals(exampleStrategy.getNameIdentifier(), desirlziedStrategy.getNameIdentifier())
        assertEquals(exampleStrategy.getDescription(),desirlziedStrategy.getDescription())
        assertEquals(exampleStrategy.getTags(),desirlziedStrategy.getTags())


    }

}