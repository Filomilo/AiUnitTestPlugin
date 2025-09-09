package org.filomilo.AiTestGenerator.LLM.Apis


//import okhttp3.RequestBody;
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.slf4j.LoggerFactory
import java.time.Duration

object ApiConnectionOKHttp : ApiConnection {
    private val logger = LoggerFactory.getLogger(ApiConnectionOKHttp::class.java)
    private val client = OkHttpClient.Builder()
        .connectTimeout(Duration.ofMinutes(55))
        .readTimeout(Duration.ofMinutes(55))
        .build()


    fun getClient(): OkHttpClient {
        return client;
    }


    override fun sendPost(url: String, content: String): String {
        try {
            val requestBody: RequestBody = content.toRequestBody("application/json; charset=utf-8".toMediaType())
            val request: Request = Request.Builder().url(url).post(requestBody).build()
            val response: Response = client.newCall(request).execute();
            if (!response.isSuccessful) {
                throw Exception("Unexpected code $response :: ${response.body!!.string()}")
            }
            return response.body!!.string()
        } catch (Ex: Exception) {
            logger.error(Ex.message)
            throw Ex
        }
    }

    override fun sendPut(url: String, content: String): String {
        val requestBody: RequestBody = RequestBody.create("application/json; charset=utf-8".toMediaType(), content)
        val request: Request = Request.Builder().url(url).put(requestBody).build()
        val response: Response = client.newCall(request).execute();
        return response.body!!.string()
    }

    override fun sendGet(url: String): String {
        try {
            logger.info("sendGet: to [[${url}]]")
            val request: Request = Request.Builder().url(url).build()
            val response: Response = client.newCall(request).execute()
            return response.body!!.string()
        } catch (ex: Exception) {
            logger.error("sendGet error:: \n" + ex.message + "\n\n" + ex.stackTrace + "\n\n")
            throw ex
        }

    }

    override fun sendDelete(url: String, content: String): String {
        val requestBody: RequestBody = content.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request: Request = Request.Builder().url(url).delete(requestBody).build()
        val response: Response = client.newCall(request).execute();
        if (!response.isSuccessful) {
            throw Exception("Unexpected code $response :: ${response.body!!.string()}")
        }
        return response.body!!.string()
    }
}