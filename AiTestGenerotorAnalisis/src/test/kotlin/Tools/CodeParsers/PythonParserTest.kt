package Tools.CodeParsers

import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeElements.PythonCodeFile
import org.filomilo.AiTestGenerator.Tools.StringTools
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PythonParserTest {

    fun pythondeCodeProvider(): Stream<Arguments> {
        return Stream.of<Arguments>(
            Arguments.of(
                """
                    import pytest

                    def add(a, b):
                        return a + b

                    pytest.test(lambda: add(2, 3))

                """
            ),
            Arguments.of(
                """
class Calculator:
    def add(a,b):
        return a+b
    def subtract(a,b):
        return a-b
    def multiply(a,b):
        return a*b
    def divide(a,b):
        if(b==0):
            raise "Cannot divide by zero"
        return a/b
        """
            )
        )
    }


    var pythonParser: PythonParser? = null

    @BeforeEach
    fun setup() {
        pythonParser = PythonParser()
    }

    @MethodSource("pythondeCodeProvider")
    @ParameterizedTest
    fun parseFileContents(content: String) {
        val pythonCodeFile: CodeFile = pythonParser!!.parseContent(content)
        assertEquals(
            StringTools.trimStringToJustSymbols(content),
            StringTools.trimStringToJustSymbols(pythonCodeFile.getContent())
        )
    }

    @Test
    fun getCodeSeparator() {
        assertEquals(
            CodeSeparator(CodeSectionStart = ":", bodyPartIndicator = "\t"),
            this.pythonParser!!.getCodeSeparator()
        )
    }

    @Test
    fun parseCodeFile() {
    }

    @Test
    fun parseContent() {
    }

}