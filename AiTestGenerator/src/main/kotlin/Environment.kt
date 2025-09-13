object Environment {

    fun shouldCacheLLM(): Boolean {
        try {
            val llmCache: String = System.getenv("LLM_CACHE")
            if (llmCache == "yes") {
                return true
            } else {
                return false
            }
        } catch (ex: Exception) {
            return false
        }
    }

}