package org.filomilo.AiTestGenerotorAnalisis


import Exceptions.LlmProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import kotlinx.serialization.Serializable
import org.filomilo.AiTestGenerotorAnalisis.Tools.PathResolver
import java.time.Instant
import java.time.LocalDateTime
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import  org.filomilo.AiTestGenerator.Tools.InstantSerializer
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = AnalysisRunSuccess::class, name = "success"),
    JsonSubTypes.Type(value = AnalysisRunFailure::class, name = "failure")
)
@Serializable
sealed class AnalysisRun {
    abstract val llmModel: String
    abstract val project: String
    abstract val strategy: String
    abstract val time: Instant


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

@Serializable
data class AnalysisRunFailure(
    val failureReason: LlmProcessingException,
    override val llmModel: String,
    override val project: String,
    override val strategy: String,
    @Serializable(with = InstantSerializer::class)
    override val time: Instant = Instant.now()
) : AnalysisRun()

@Serializable
data class AnalysisRunSuccess(
    override val llmModel: String,
    override val project: String,
    override val strategy: String,
    @Serializable(with = InstantSerializer::class)
    override val time: Instant = Instant.now(),
    val report: TestReport
) : AnalysisRun()


@Serializable
data class AnalysisResults(
    var runs: HashSet<AnalysisRun> = hashSetOf<AnalysisRun>(),
    var fails: HashSet<AnalysisRun> = hashSetOf<AnalysisRun>()
) {
    private val log: Logger = LoggerFactory.getLogger(AnalysisResults::class.java)

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AnalysisResults::class.java)

        val filePath = PathResolver.getResultFilePath()
        val mapper: JsonMapper = JsonMapper()
        public fun load(): AnalysisResults {
            try {
                if (!filePath.toFile().exists()) {
                    return AnalysisResults()
                }
                val fileContent: String = filePath.toFile().readText()
                val analysisResults: AnalysisResults = mapper.readValue(fileContent, AnalysisResults::class.java)
                return analysisResults
            } catch (ex: Exception) {
                log.info("Error while reading analysisResults" + ex.message)
                return AnalysisResults();
            }
        }

        init {
            mapper.registerModule(JavaTimeModule());
        }

    }

    fun save() {


        val content = mapper.writeValueAsString(this)
        log.info("save:: content [[$content]] to path [[$filePath.toAbsolutePath()]] ")
        filePath.toFile().writeText(content)
    }

    fun addRun(analysisRun: AnalysisRunSuccess) {
        log.info("addRun:: [[$analysisRun]] ")
        if (this.runs.contains(analysisRun)) {
            this.runs.remove(analysisRun)
        }
        this.runs.add(analysisRun)
        if (this.fails.contains(analysisRun)) {
            this.fails.remove(analysisRun)
        }
    }

    fun addRunFailure(analysisRun: AnalysisRunFailure) {
        log.info("addRunFailure:: [[$analysisRun]] ")
        if (this.fails.contains(analysisRun)) {
            this.fails.remove(analysisRun)
        }
        this.fails.add(analysisRun)
    }
}



