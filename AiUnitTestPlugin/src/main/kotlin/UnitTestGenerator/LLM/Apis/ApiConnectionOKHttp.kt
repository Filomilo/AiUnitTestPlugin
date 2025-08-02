package UnitTestGenerator.LLM.Apis


import okhttp3.*
import okhttp3.MediaType;
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
//import okhttp3.RequestBody;
import org.jetbrains.io.response
import java.time.Duration

object ApiConnectionOKHttp : ApiConnection {
    private val client = OkHttpClient.Builder()
        .connectTimeout(Duration.ofMinutes(15))
        .readTimeout(Duration.ofMinutes(15))
        .build()


    fun getClient(): OkHttpClient {
        return client;
    }


    override fun sendPost(url: String, content: String): String {
        val requestBody: RequestBody = content.toRequestBody("application/json; charset=utf-8".toMediaType())
        val request: Request = Request.Builder().url(url).post(requestBody).build()
        val response: Response = client.newCall(request).execute();
        if (!response.isSuccessful) {
            throw Exception("Unexpected code $response :: ${response.body!!.string()}")
        }
        return response.body!!.string()
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
}