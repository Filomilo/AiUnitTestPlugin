package UnitTestGenerator.LLM.Apis

import org.junit.Ignore
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled

class ApiConnectionOKHttpTest {
    @Test
//    @Disabled("temporaryl")
    fun testApis() {
        assertNotNull(ApiConnectionOKHttp.getClient())
        val elem = ApiConnectionOKHttp.getClient()
        assertNotNull(elem)
    }

}