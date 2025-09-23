package org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy

import com.fasterxml.jackson.annotation.JsonProperty
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRun
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import Tools.PathResolver
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.filomilo.AiTestGenerator.Tools.PathObject
import java.nio.file.Path






@Serializable(with = TestGenerationStrategySerializer::class)
interface TestGenerationStrategy {

    enum class Tags{
        DUMMY,
    }

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
    @JsonProperty("Tags")
    fun getTags(): List<Tags>
}

data class TestGenerationStrategyBasicContainer(private val name:String, private val description:String, private val tags: List<TestGenerationStrategy.Tags>):TestGenerationStrategy {
    override fun getNameIdentifier(): String {
    return name
    }

    override fun getDescription(): String {
      return description
    }

    override fun runTestGenerationStrategy(
        llmProcessor: LLMProcessor,
        project: Project
    ): AnalysisRun {
        throw UnsupportedOperationException("this class is data container only")
    }

    override fun getWarnings(): Collection<Exception> {
        throw UnsupportedOperationException("this class is data container only")
    }

    override fun getPromptResults(): HashSet<LLMResponse>? {
        throw UnsupportedOperationException("this class is data container only")
    }

    override fun clearBuffers() {
        throw UnsupportedOperationException("this class is data container only")
    }

    override fun getGeneratedFiles(): List<PathObject>? {
        throw UnsupportedOperationException("this class is data container only")
    }

    override fun getExecutionLogs(): List<String>? {
        throw UnsupportedOperationException("this class is data container only")
    }

    override fun getTags(): List<TestGenerationStrategy.Tags> {
return tags
    }

}



@Serializer(forClass = TestGenerationStrategy::class)
object TestGenerationStrategySerializer : KSerializer<TestGenerationStrategy> {
override val descriptor: SerialDescriptor= buildClassSerialDescriptor("TestGenerationStrategy"){
    element<String>("name")
    element<String>("description")
    element<List<String>>("tags")
}

    override fun serialize(encoder: Encoder, value: TestGenerationStrategy) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.getNameIdentifier())
            encodeStringElement(descriptor, 1, value.getDescription())
            encodeSerializableElement(descriptor, 2, ListSerializer(String.serializer()), value.getTags().map { x->x.name })

        }
    }

    override fun deserialize(decoder: Decoder): TestGenerationStrategy {
        return decoder.decodeStructure(descriptor) {
            var name: String?=null
            var description: String?=null
            var tagsstr: List<String>?=null
            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    DECODE_DONE -> break@loop

                    0 -> name = decodeStringElement(descriptor, 0)
                    1 -> description = decodeStringElement(descriptor, 1)
                    2 -> tagsstr = decodeSerializableElement(descriptor, 2, ListSerializer(String.serializer()))
                    else -> throw SerializationException("Unexpected index $index")
                }
            }

            TestGenerationStrategyBasicContainer(
             name=name!!,
                description=description!!,
                tags =  tagsstr!!.map { x-> enumValues<TestGenerationStrategy.Tags>().firstOrNull{it.name==x}!!}.toList()
            )
        }
    }
}



