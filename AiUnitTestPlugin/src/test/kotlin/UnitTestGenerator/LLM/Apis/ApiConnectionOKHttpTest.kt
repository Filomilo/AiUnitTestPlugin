package UnitTestGenerator.LLM.Apis

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class ApiConnectionOKHttpTest{
    @Test
    fun testApis(){
        assertNotNull(      ApiConnectionOKHttp.getClient())
    val elem=ApiConnectionOKHttp.getClient()
        assertNotNull( elem)
    }

}