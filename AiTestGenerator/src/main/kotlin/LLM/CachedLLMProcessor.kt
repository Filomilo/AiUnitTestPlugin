package org.filomilo.AiTestGenerator.LLM


import DeviceSpecification
import Tools.PathResolver
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import kotlinx.serialization.json.Json

import org.filomilo.AiTestGenerator.Tools.StringTools
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.exists
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


class CachedLLMProcessor(llmProcessor: LLMProcessor) : LLMProcessor {
    private val llmProcessor: LLMProcessor = llmProcessor

    private var durationGeneratorBuffer: MutableList<Duration> = mutableListOf<Duration>()

    companion object {
        private val cacheFile: Path = PathResolver.getMainProjectDirectory().resolve("LLMcache.cache")

        private lateinit var cachedData: HashSet<LLMResponse>

        init {
            if (!cacheFile.exists()) {
                Files.createFile(cacheFile)
                this.cachedData = HashSet<LLMResponse>()
            } else {
                var cachedFileContent = Files.readString(cacheFile)
                if (cachedFileContent.isEmpty()) {
                    this.cachedData = HashSet<LLMResponse>()
                } else {
                    this.cachedData = Json.decodeFromString<HashSet<LLMResponse>>(
                        cachedFileContent
                    )
                    cachedData.forEach { x -> x.response = StringTools.turnEscapreSeqanceIntoChars(x.response) }
                }

            }

        }

        private fun updateCacheFile() {
            val json = Json {
                prettyPrint = true
                prettyPrintIndent = "  "
            }
            var newCacheContent: String = json.encodeToString(cachedData)
            Files.write(cacheFile, newCacheContent.toByteArray(Charsets.UTF_8))
        }

        private fun retrievePromoterCache(
            prompt: String,
            modelName: String,
            device: DeviceSpecification?
        ): LLMResponse? {
            var retrived: LLMResponse? = this.cachedData.find { x -> x.compareConfig(prompt, modelName, device) }

            return retrived
        }

        private fun addPromptToCache(llmResponse: LLMResponse) {
            this.cachedData.add(llmResponse)
            updateCacheFile()
        }

    }


    private fun getModelName(): String {
        return this.llmProcessor.getName()
    }


    override fun executePrompt(prompt: String): LLMResponse {
        var result: LLMResponse? =
            retrievePromoterCache(prompt, getModelName(), this.llmProcessor.getDeviceSpecification())
        if (result != null) {
            this.durationGeneratorBuffer.add(result.generationTime)
            return result
        }
        result = this.llmProcessor.executePrompt(prompt)
        addPromptToCache(result)
        return result
    }

    public fun emptyDurationBuffer(): Duration {
        var generatorDuration: Duration = 0.seconds
        this.durationGeneratorBuffer.forEach { x ->
            generatorDuration += x
        }
        this.durationGeneratorBuffer = mutableListOf<Duration>()
        return generatorDuration


    }


    override fun load() {
        this.llmProcessor.load()
    }

    override fun unload() {
        this.llmProcessor.unload()

    }

    override fun getName(): String {
        return "cache-" + this.llmProcessor.getName()
    }

    override fun getDeviceSpecification(): DeviceSpecification? {
        return this.llmProcessor.getDeviceSpecification()
    }

    override fun toString(): String {
        return this.llmProcessor.toString() + "-cached"
    }
}