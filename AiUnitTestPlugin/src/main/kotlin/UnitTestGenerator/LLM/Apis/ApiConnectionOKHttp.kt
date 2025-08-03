package UnitTestGenerator.LLM.Apis


import okhttp3.*
import okhttp3.MediaType;
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
//import okhttp3.RequestBody;
import org.jetbrains.io.response
import org.slf4j.LoggerFactory
import java.time.Duration
import kotlin.math.log2

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
        val request: Request = Request.Builder().url(url).build()
        val response: Response = client.newCall(request).execute()
        return response.body!!.string()
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