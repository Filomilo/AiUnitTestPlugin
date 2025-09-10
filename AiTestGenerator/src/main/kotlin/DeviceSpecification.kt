import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class DeviceSpecification(
    val ramBytes: Long,
    val system: String,
    val processor: String,
    val gpu: String
) {

}
