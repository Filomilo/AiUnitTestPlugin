package UnitTestGenerator.LLM.Containers

interface Container {
    fun start()
    fun stop()
    fun destroy()

    fun getStatus(): VmStatus

    fun getRamMb(): Int
}