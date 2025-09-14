package org.filomilo.AiTestGenerotorAnalisis


import DeviceSpecification
import Exceptions.LlmProcessingException
import kotlinx.serialization.Serializable
import Tools.PathResolver
import kotlinx.serialization.Contextual
import java.time.Instant
import  org.filomilo.AiTestGenerator.Tools.InstantSerializer
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import org.filomilo.AiTestGenerator.LLM.LLMResponse
import org.filomilo.AiTestGenerator.Tools.PathObject
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.slf4j.Logger
import org.slf4j.LoggerFactory

//@JsonTypeInfo(
//    use = JsonTypeInfo.Id.NAME,
//    include = JsonTypeInfo.As.PROPERTY,
//    property = "type"
//)
//@JsonSubTypes(
//    JsonSubTypes.Type(value = AnalysisRunSuccess::class, name = "success"),
//    JsonSubTypes.Type(value = AnalysisRunFailure::class, name = "failure")
//)


@Serializable
@Polymorphic
data class AnalysisRun(
    val failureReason: LlmProcessingException?,
    val llmModel: String,
    val project: String,
    val strategy: String,
    @Serializable(with = InstantSerializer::class)
    val time: Instant = Instant.now(),
    val deviceSpecification: DeviceSpecification?,
    var executionLogs: List<String>? = null,
    var warnings: Collection<@Serializable(with = ExceptionSerializer::class) Exception> = emptyList(),
    var promptResults: HashSet<@Contextual LLMResponse>? = null,
    var generatedFiles: List<PathObject>? = null,
    var duration: kotlin.time.Duration? = null,
    val report: TestReport?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AnalysisRun

        if (llmModel != other.llmModel) return false
        if (project != other.project) return false
        if (strategy != other.strategy) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 0
//        result = 31 * result +  time.dayOfMonth.hashCode()
//        result = 31 * result + time.month.hashCode()
//        result = 31 * result + time.year.hashCode()
        result = 31 * result + llmModel.hashCode()
        result = 31 * result + project.hashCode()
        result = 31 * result + strategy.hashCode()
        return result
    }
}

//@SerialName("failure")
//@Serializable
//data class AnalysisRunFailure(
//    val failureReason: LlmProcessingException,
//    override val llmModel: String,
//    override val project: String,
//    override val strategy: String,
//    @Serializable(with = InstantSerializer::class)
//    override val time: Instant = Instant.now(),
//    override val deviceSpecification: DeviceSpecification?,
//    override var executionLogs: List<String>? = null,
//
//    override val warnings: Collection<@Serializable(with = ExceptionSerializer::class) Exception> = emptyList(),
//
//    override var promptResults: HashSet<@Contextual LLMResponse>? = null,
//    override var generatedFiles: List<PathObject>? = null,
//) : AnalysisRun()
//
//@SerialName("success")
//@Serializable
//data class AnalysisRunSuccess(
//    override val llmModel: String,
//    override val project: String,
//    override val strategy: String,
//    @Serializable(with = InstantSerializer::class)
//    override val time: Instant = Instant.now(),
//    val report: TestReport, override val deviceSpecification: DeviceSpecification?,
//    var duration: kotlin.time.Duration? = null,
//    override val executionLogs: List<String>? = null,
//    override var warnings: Collection<@Serializable(with = ExceptionSerializer::class) Exception> = emptyList(),
//    override val promptResults: HashSet<@Contextual LLMResponse>?,
//    override val generatedFiles: List<PathObject>? = null
//) : AnalysisRun()


@Serializable
data class AnalysisResults(
    var runs: HashSet<AnalysisRun> = hashSetOf<AnalysisRun>(),
    var fails: HashSet<AnalysisRun> = hashSetOf<AnalysisRun>()
) {
    private val log: Logger = LoggerFactory.getLogger(AnalysisResults::class.java)

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AnalysisResults::class.java)

        val filePath = PathResolver.getResultFilePath()

        public fun load(): AnalysisResults {
            try {
                if (!filePath.toFile().exists()) {
                    return AnalysisResults()
                }
                val fileContent: String = filePath.toFile().readText()
                val analysisResults: AnalysisResults = Json.decodeFromString<AnalysisResults>(fileContent)
                return analysisResults
            } catch (ex: Exception) {
                log.info("Error while reading analysisResults" + ex.message)
                return AnalysisResults();
            }
        }


    }

    fun save() {


        val content = Json.encodeToString(this)
        log.info("save:: content [[$content]] to path [[$filePath.toAbsolutePath()]] ")
        filePath.toFile().writeText(content)
    }

    fun addRun(analysisRun: AnalysisRun) {
        log.info("addRun:: [[$analysisRun]] ")
        if (this.runs.contains(analysisRun)) {
            this.runs.remove(analysisRun)
        }
        this.runs.add(analysisRun)
        if (this.fails.contains(analysisRun)) {
            this.fails.remove(analysisRun)
        }
    }

    fun addRunFailure(analysisRun: AnalysisRun) {
        log.info("addRunFailure:: [[$analysisRun]] ")
        if (this.fails.contains(analysisRun)) {
            this.fails.remove(analysisRun)
        }
        this.fails.add(analysisRun)
    }
}



