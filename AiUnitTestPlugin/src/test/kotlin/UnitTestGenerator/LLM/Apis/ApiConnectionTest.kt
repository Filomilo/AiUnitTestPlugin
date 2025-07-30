package UnitTestGenerator.LLM.Apis

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class ApiConnectionTest{
    @Test
    fun testApis(){
        assertNotNull(      ApiConnection.getClient())
    val elem=ApiConnection.getClient()
        assertNotNull( elem)
    }

}