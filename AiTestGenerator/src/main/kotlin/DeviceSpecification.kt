import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import org.slf4j.LoggerFactory
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;


@Serializable
data class Cpu(
    val name: String,
    val physicalCores: Int?,
    val logicalCores: Int?
)

@Serializable
data class Gpu(
    val name: String,
    val VRamBytes: Long
)


@Serializable
data class DeviceSpecification(
    val ramBytes: Long,
    val system: String,
    val processor: Cpu,
    val gpu: Collection<Gpu>
) {
    companion object {

        val log = LoggerFactory.getLogger(DeviceSpecification.javaClass)


        fun getCurrentDeviceSpecification(): DeviceSpecification {
            val si: SystemInfo = SystemInfo();
            val hal: HardwareAbstractionLayer = si.getHardware();
            val cpu: CentralProcessor = hal.getProcessor();
            val memory: GlobalMemory = hal.getMemory();

            var gpus: MutableList<Gpu> = mutableListOf<Gpu>()
            for (gpu: GraphicsCard in hal.getGraphicsCards()) {
                gpus.add(
                    Gpu(
                        name = gpu.getName(),
                        VRamBytes = gpu.getVRam(),
                    )
                )
            }



            return DeviceSpecification(
                ramBytes = memory.getTotal(),
                system = System.getProperty("os.name"),
                processor = Cpu(
                    name = cpu.getProcessorIdentifier().getName(),
                    physicalCores = cpu.getPhysicalProcessorCount(),
                    logicalCores = cpu.getLogicalProcessorCount(),
                ),
                gpu = gpus
            )
        }
    }
}