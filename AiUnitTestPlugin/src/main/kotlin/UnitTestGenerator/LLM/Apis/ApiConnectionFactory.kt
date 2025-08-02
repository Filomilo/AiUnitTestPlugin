package UnitTestGenerator.LLM.Apis

object ApiConnectionFactory {

    fun getApiConnector(): ApiConnection {
        return ApiConnectionOKHttp;
    }

}