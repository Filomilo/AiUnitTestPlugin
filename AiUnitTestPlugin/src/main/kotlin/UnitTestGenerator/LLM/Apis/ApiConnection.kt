package UnitTestGenerator.LLM.Apis

//import java.io.IOException;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
import okhttp3.*;
import okhttp3.OkHttpClient

object ApiConnection {
    private val client = OkHttpClient()
    fun getClient(): OkHttpClient{
        return  client;
    }
}