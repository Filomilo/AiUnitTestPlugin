package org.filomilo.AiTestGenerator.LLM.Apis

object ApiConnectionFactory {

    fun getApiConnector(): ApiConnection {
        return ApiConnectionOKHttp;
    }

}