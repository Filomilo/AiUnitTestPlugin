import LLM.Apis.Ollama.OllamaApiGenerator
import jdk.incubator.vector.VectorOperators.LOG
import org.filomilo.AiTestGenerator.LLM.Apis.ApiConnectionFactory
import org.filomilo.AiTestGenerator.LLM.Apis.Ollama.OllamaApi
import org.filomilo.AiTestGenerator.LLM.CachedLLMProcessor
import org.filomilo.AiTestGenerator.LLM.Containers.ContainersManager
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.LLM.LLMProcessor
import org.filomilo.AiTestGenerator.LLM.LlmRepository
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRunner
import org.filomilo.AiTestGenerotorAnalisis.AnalysisRunner.containerManager
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectsRepository
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.Strategy.TestGenerationStrategy
import org.filomilo.AiTestGenerotorAnalisis.TestGeneration.TestGenerationStrategyRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import java.util.stream.Stream


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnalysisRunnerTest {


    companion object {
        val log: org.slf4j.Logger = LoggerFactory.getLogger(AnalysisRunnerTest::class.java)
//        lateinit var LlmRepository: LlmRepository;
//        lateinit var ollamaApi: OllamaApi
//        lateinit var containerManager: ContainersManager;

        @JvmStatic
        @BeforeAll
        fun setup(): Unit {
            log.info("DockerConnection setup")
//            this.containerManager = DockerConnection
//            log.info("llamaApiGenerator.getOllamaApi()")
//            this.ollamaApi = OllamaApiGenerator.getOllamaApi()
//            log.info("LlmRepository()")
//            this.LlmRepository = LlmRepository(
//                containerManager,
//                ApiConnectionFactory.getApiConnector(),
//                this.ollamaApi
//            )
//            log.info("            this.LlmRepository.initlize()")
//            this.LlmRepository.initlize()
        }

        @JvmStatic
        @AfterAll
        fun tearDown(): Unit {
//            containerManager.getRunningContainersList().forEach { x -> containerManager.destroyContainer(x) }
        }
    }


//    fun provideProjetLlmStratefyCombinations(): Stream<Arguments> {
////        var argslist: MutableList<Arguments> = mutableListOf<Arguments>()
////        for (llm: LLMProcessor in LlmRepository.ListOfLlmProcessors) {
////            var cachedLLMProcessor: LLMProcessor = CachedLLMProcessor(llm)
////            for (strategy: TestGenerationStrategy in TestGenerationStrategyRepository.strategies) {
////                for (project: Project in ProjectsRepository.projects) {
////                    argslist.add(Arguments.of(cachedLLMProcessor, strategy, project))
////                }
////            }
////        }
////        return argslist.stream()
//    }


    @Disabled("tooo long")
    @ParameterizedTest
    @Timeout(15, unit = TimeUnit.MINUTES)
    @MethodSource("provideProjetLlmStratefyCombinations")
    fun runStrategyOnLLMProcessorOnProejctTest(
        llmProcessor: LLMProcessor,
        strategy: TestGenerationStrategy,
        project: Project
    ) {
//        llmProcessor.load()
//        log.info(
//            """
//
//            -------------------------------------------------------------------
//
//            Running test strategy: [[${strategy.getNameIdentifier()}]]
//            on lmm: [[$llmProcessor]]
//            with project: [[${project.name}]]
//
//            -------------------------------------------------------------------
//        """.trimIndent()
//        )
//        val countSucessBeforeTest = AnalysisRunner.analysisResults.runs.count()
//        val counFailsBeforeTest = AnalysisRunner.analysisResults.fails.count()
//        AnalysisRunner.runStrategyOnLLMProcessorOnProejct(
//            llmProcessor, strategy, project
//        )
//
//        val countSucessAfterTest = AnalysisRunner.analysisResults.runs.count()
//        val counFailsAfterTest = AnalysisRunner.analysisResults.fails.count()
//
//        assertEquals(counFailsBeforeTest + countSucessBeforeTest + 1, countSucessAfterTest + counFailsAfterTest)
//        llmProcessor.unload()

    }
}