package org.filomilo.AiTestGenerator.LLM.Containers.Config;


data class ContainerConfiguration(
    val image: String = "",
    val ramBytes: Long? = null,
    val portConfiguration: List<ExposedPort> = ArrayList<ExposedPort>(),
    val mountVolumes: List<MountVolume> = ArrayList<MountVolume>(),
    val gpus: Boolean =false
)
