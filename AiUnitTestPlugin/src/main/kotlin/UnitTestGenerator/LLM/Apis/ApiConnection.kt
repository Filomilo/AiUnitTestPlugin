package UnitTestGenerator.LLM.Apis

interface ApiConnection {
    suspend fun sendPost(url: String, content: String): String
    suspend fun sendPut(url: String, content: String): String
    suspend fun sendGet(url: String): String

}