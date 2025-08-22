package org.filomilo.AiTestGenerator.LLM.Apis

interface ApiConnection {
    fun sendPost(url: String, content: String): String
    fun sendPut(url: String, content: String): String
    fun sendGet(url: String): String
    fun sendDelete(url: String, content: String): String

}