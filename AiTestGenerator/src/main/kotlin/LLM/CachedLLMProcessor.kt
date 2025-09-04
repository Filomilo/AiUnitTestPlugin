package org.filomilo.AiTestGenerator.LLM


import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import com.intellij.credentialStore.toByteArrayAndClear
import kotlinx.html.emptyMap
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Collections.emptyMap
import kotlin.collections.containsKey
import kotlin.collections.get
import kotlin.collections.mapOf
import kotlin.io.path.exists


class CachedLLMProcessor(llmProcessor: LLMProcessor ): LLMProcessor
{
    private val llmProcessor: LLMProcessor= llmProcessor
    companion object{
        private val cacheFile:Path= Paths.get("LLMcache.cache")

        private lateinit var cachedData: MutableMap<Int,MutableMap<String, String>>
        private   val mapper: JsonMapper = JsonMapper()
        init {
            if(!cacheFile.exists())
            {
                Files.createFile(cacheFile)
                this.cachedData=HashMap()
            }
            else{
                var cachedFileContent= Files.readString(cacheFile)
                if(cachedFileContent.isEmpty())
                {
                    this.cachedData= HashMap()
                }
                else{
                    this.cachedData= mapper.readValue(
                        cachedFileContent,
                        object : TypeReference<MutableMap<Int,MutableMap<String, String>>>() {}
                    )
                }

            }

        }

        private fun updateCacheFile()
        {
            var newCacheContent:String= mapper.writeValueAsString(cachedData)
            Files.write(cacheFile,  newCacheContent.toByteArray(Charsets.UTF_8))
        }

        private fun retrievePromoterCache(prompt:String, modelHash:Int): String?{
            if(!this.cachedData.containsKey(modelHash))
            {
                return null
            }
            val thisLLmCachedPrompts: Map<String, String> = this.cachedData.get(modelHash)!!
            if(!thisLLmCachedPrompts.containsKey(prompt))
            {
                return null
            }
            return thisLLmCachedPrompts.get(prompt)
        }

        private fun addPromptToCache(prompt: String, result:String, modelHash:Int){
            if(!this.cachedData.containsKey(modelHash))
            {
                var emptyMap:MutableMap<String, String> = HashMap()
                this.cachedData.put(modelHash,emptyMap)

            }
            this.cachedData[modelHash]!!.put(prompt,result)
            updateCacheFile()
        }

    }


    private  fun getModelHash():Int{
        return  this.llmProcessor.hashCode()
    }



    override fun executePrompt(prompt: String): String {
        var result: String?= retrievePromoterCache(prompt,getModelHash())
        if(result!=null)
        {
            return result
        }
        result=this.llmProcessor.executePrompt(prompt)
        addPromptToCache(prompt,result,getModelHash())
        return result
    }

    override fun load() {
        this.llmProcessor.load()
    }

    override fun unload() {
        this.llmProcessor.unload()

    }
}