package org.filomilo.AiTestGenerotorAnalisis

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

@Serializer(forClass = Exception::class)
object ExceptionSerializer : KSerializer<Exception> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Exception") {
            element<String>("type")
            element<String?>("message")
        }

    override fun serialize(encoder: Encoder, value: Exception) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeStringElement(descriptor, 0, value::class.qualifiedName ?: "Exception")
        composite.encodeNullableSerializableElement(
            descriptor,
            1,
            kotlinx.serialization.serializer<String?>(),
            value.message
        )
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): Exception {
        val dec = decoder.beginStructure(descriptor)
        var type: String? = null
        var message: String? = null

        loop@ while (true) {
            when (val index = dec.decodeElementIndex(descriptor)) {
                CompositeDecoder.DECODE_DONE -> break@loop
                0 -> type = dec.decodeStringElement(descriptor, 0)
                1 -> message = dec.decodeNullableSerializableElement(descriptor, 1, kotlinx.serialization.serializer())
            }
        }

        dec.endStructure(descriptor)

        return Exception("$type: $message")
    }
}