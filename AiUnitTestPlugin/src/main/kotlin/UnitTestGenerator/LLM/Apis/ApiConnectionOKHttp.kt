package UnitTestGenerator.LLM.Apis


import okhttp3.*
import okhttp3.MediaType;
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.RequestBody;
import org.jetbrains.io.response

object ApiConnectionOKHttp : ApiConnection {
    private val client = OkHttpClient()
    fun getClient(): OkHttpClient {
        return client;
    }


    override suspend fun sendPost(url: String, content: String): String {
        val requestBody: RequestBody = RequestBody.create("application/json; charset=utf-8".toMediaType(), content)
        val request: Request = Request.Builder().url(url).post(requestBody).build()
        val response: Response = client.newCall(request).execute();
        return response.body!!.string()
    }

    override suspend fun sendPut(url: String, content: String): String {
        val requestBody: RequestBody = RequestBody.create("application/json; charset=utf-8".toMediaType(), content)
        val request: Request = Request.Builder().url(url).put(requestBody).build()
        val response: Response = client.newCall(request).execute();
        return response.body!!.string()
    }

    override suspend fun sendGet(url: String): String {
        val request: Request = Request.Builder().url(url).build()
        val response: Response = client.newCall(request).execute()
        return response.body!!.string()
    }
}