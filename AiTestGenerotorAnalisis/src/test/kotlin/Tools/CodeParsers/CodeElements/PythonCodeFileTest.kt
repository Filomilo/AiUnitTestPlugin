package Tools.CodeParsers.CodeElements

import Tools.CodeParsers.PythonParser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PythonCodeFileTest {
    @Test
    fun getContent() {
        val pythonCode = """

class Calculator:
	def add(self,a,b):
		c=a+b
		return c

	def subtract(self,a,b):
		return a-b

	def multiply(self,a,b):
		return a*b

	def divide(self,a,b):
		if(b==0):
		    raise "Cannot divide by zero"
		return a/b
        """.trimEnd().trimStart()
        val codeFile = PythonParser().parseContent(pythonCode)
        val parseContent: String = codeFile.getContent().trimEnd().trimStart()
        assertEquals(pythonCode, parseContent)
    }


}