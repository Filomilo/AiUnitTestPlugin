package org.filomilo.AiTestGenerator.LLM

import DeviceSpecification
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import org.filomilo.AiTestGenerator.Tools.StringTools
import org.jdom.filter2.Filters.element
import kotlin.time.Duration


@Serializer(forClass = LLMResponse::class)
object LLMResponseSerializer : KSerializer<LLMResponse> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("LLMResponse") {
        element<String>("prompt")
        element<String>("response")
        element<String>("modelName")
        element<String>("generationTime") // store as String
        element<DeviceSpecification?>("deviceSpecification", isOptional = true)
    }


    override fun serialize(encoder: Encoder, value: LLMResponse) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.prompt)
            encodeStringElement(descriptor, 1, value.response)
            encodeStringElement(descriptor, 2, value.modelName)
            encodeStringElement(descriptor, 3, value.generationTime.toString()) // Duration as ISO string
            if (value.deviceSpecification != null) {
                encodeSerializableElement(descriptor, 4, DeviceSpecification.serializer(), value.deviceSpecification)
            }
        }
    }

    override fun deserialize(decoder: Decoder): LLMResponse {
        var prompt = ""
        var response = ""
        var modelName = ""
        var generationTime: Duration = Duration.ZERO
        var deviceSpecification: DeviceSpecification? = null

        decoder.decodeStructure(descriptor) {
            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    CompositeDecoder.DECODE_DONE -> break@loop
                    0 -> prompt = decodeStringElement(descriptor, 0)
                    1 -> response = StringTools.turnCharsIntoEscapeSequance(decodeStringElement(descriptor, 1))
                    2 -> modelName = decodeStringElement(descriptor, 2)
                    3 -> generationTime = Duration.parse(decodeStringElement(descriptor, 3))
                    4 -> deviceSpecification =
                        decodeSerializableElement(descriptor, 4, DeviceSpecification.serializer())

                    else -> throw IllegalStateException("Unexpected index: $index")
                }
            }
        }

        return LLMResponse(prompt, response, modelName, generationTime, deviceSpecification)
    }
}


@Serializable(with = LLMResponseSerializer::class)
data class LLMResponse(
    val prompt: String,
    var response: String,
    val modelName: String,
    val generationTime: Duration,
    val deviceSpecification: DeviceSpecification?
) {
    fun compareConfig(prompt: String, modelName: String, device: DeviceSpecification?): Boolean {
        return prompt == this.prompt && modelName == this.modelName && this.deviceSpecification == device
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LLMResponse

        if (prompt != other.prompt) return false
        if (response != other.response) return false
        if (modelName != other.modelName) return false
        if (deviceSpecification != other.deviceSpecification) return false

        return true
    }

    override fun hashCode(): Int {
        var result = prompt.hashCode()
        result = 31 * result + response.hashCode()
        result = 31 * result + modelName.hashCode()
        result = 31 * result + (deviceSpecification?.hashCode() ?: 0)
        return result
    }


}


interface LLMProcessor {
    fun executePrompt(prompt: String): LLMResponse
    fun load()
    fun unload()
    fun getName(): String
    fun getDeviceSpecification(): DeviceSpecification?
}