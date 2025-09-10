package org.filomilo.AiTestGenerator.LLM


import DeviceSpecification
import Tools.PathResolver
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper

import org.filomilo.AiTestGenerator.Tools.StringTools
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.exists


class CachedLLMProcessor(llmProcessor: LLMProcessor) : LLMProcessor {
    private val llmProcessor: LLMProcessor = llmProcessor

    companion object {
        private val cacheFile: Path = PathResolver.getMainProjectDirectory().resolve("LLMcache.cache")

        private lateinit var cachedData: MutableMap<String, MutableMap<String, String>>
        private val mapper: JsonMapper = JsonMapper()

        init {
            if (!cacheFile.exists()) {
                Files.createFile(cacheFile)
                this.cachedData = HashMap()
            } else {
                var cachedFileContent = Files.readString(cacheFile)
                if (cachedFileContent.isEmpty()) {
                    this.cachedData = HashMap()
                } else {
                    this.cachedData = mapper.readValue(
                        cachedFileContent,
                        object : TypeReference<MutableMap<String, MutableMap<String, String>>>() {}
                    )
                }

            }

        }

        private fun updateCacheFile() {
            var newCacheContent: String = mapper.writeValueAsString(cachedData)
            Files.write(cacheFile, newCacheContent.toByteArray(Charsets.UTF_8))
        }

        private fun retrievePromoterCache(prompt: String, modelName: String): String? {
            if (!this.cachedData.containsKey(modelName)) {
                return null
            }
            val thisLLmCachedPrompts: Map<String, String> = this.cachedData.get(modelName)!!
            if (!thisLLmCachedPrompts.containsKey(prompt)) {
                return null
            }
            return thisLLmCachedPrompts.get(prompt)
        }

        private fun addPromptToCache(prompt: String, result: String, modelName: String) {
            if (!this.cachedData.containsKey(modelName)) {
                var emptyMap: MutableMap<String, String> = HashMap()
                this.cachedData.put(modelName, emptyMap)

            }
            this.cachedData[modelName]!!.put(prompt, result)
            updateCacheFile()
        }

    }


    private fun getModelName(): String {
        return this.llmProcessor.getName()
    }


    override fun executePrompt(prompt: String): String {
        var result: String? = retrievePromoterCache(prompt, getModelName())
        if (result != null) {
            return StringTools.turnEscapreSeqanceIntoChars(result)
        }
        result = this.llmProcessor.executePrompt(prompt)
        addPromptToCache(prompt, result, getModelName())
        return result
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