package UnitTestGenerator.LLM.Containers.Config;


data class ContainerConfiguration(
    val image: String = "",
    val ramBytes: Long = 128 * 1024 * 1024,
    val portConfiguration: List<ExposedPort> = ArrayList<ExposedPort>()
)
