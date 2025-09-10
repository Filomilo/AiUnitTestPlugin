package CodeParsers

import LLM.LlmParser
import LlmResultSpecification
import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeElements.JavaCodeFile
import Tools.CodeParsers.CodeParser
import Tools.CodeParsers.ParsingException
import org.filomilo.AiTestGenerator.Tools.CodeParsers.JavaParser
import org.filomilo.AiTestGenerator.Tools.StringTools
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.platform.commons.logging.LoggerFactory
import java.nio.file.Paths

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JavaParserTest {

    companion object {
        var log = LoggerFactory.getLogger(JavaParserTest::class.java)
    }

    val testFileConent_Calculator: String = """
        package org.filomilo.AiTestGenerotorAnalisis;

public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }
    public static int subtract(int a, int b) {
        return a - b;
    }
    public static int multiply(int a, int b) {
        return a * b;
    }
    public static int divide(int a, int b) {
        if (b == 0)
            throw new ArithmeticException("Divide by zero");
        return a / b;
    }

    public static float multiply(float a, float b) {
        return a * b;
    }
    public static float divide(float a, float b) {
        if(b == 0)
            throw new ArithmeticException("Divide by zero");
        return a / b;
    }
    public static float add(float a, float b) {
        return a + b;
    }
    public static float subtract(float a, float b) {
        return a - b;
    }



    public static double multiply(double a, double b) {
        return a * b;
    }
    public static double divide(double a, double b) {
        if(b == 0)
            throw new ArithmeticException("Divide by zero");
        return a / b;
    }
    public static double add(double a, double b) {
        return a + b;
    }
    public static double subtract(double a, double b) {
        return a - b;
    }


}
"""

    private fun trimString(a: String): String {
        return a.replace(Regex("\\s+"), " ").replace("\r\n", "").replace("\n", "")
            .replace("\t", "").replace(" ", "")
            .replace(Regex("\\s+"), " ");
    }

    @Test
    fun parseJavaFile() {
    }

    @Test
    fun parseFileContents() {
        var JavaCodeFile: JavaCodeFile = JavaParser.parseFileContents(this.testFileConent_Calculator)
        assertEquals(
            trimString("package org.filomilo.AiTestGenerotorAnalisis;"),
            trimString(JavaCodeFile.packageDelaration!!)
        )
        assertEquals(1, JavaCodeFile.codes.size)
        assertEquals(trimString(testFileConent_Calculator), trimString(JavaCodeFile.getContent()))
    }

    @ParameterizedTest
    @MethodSource("DataProviders#provideLlmResults")
    fun parseLLmResults(llmResult: String, llmResultSpecification: LlmResultSpecification) {
        var codeFiles: MutableList<CodeFile> = listOf<CodeFile>().toMutableList()
        for (code: String in LlmParser.extractListingFromLlmResponse(llmResult)) {
            val txtWithEscapeSeq: String = StringTools.turnCharsIntoEscapeSequance(code)
            log.info { "parse llm code:: \n[[\n$txtWithEscapeSeq\n]]\n\n" }
            val codeParser: CodeParser = DataProviders.getFileParser(llmResultSpecification.language)
            try {
                val codeFile: CodeFile = codeParser.parseContent(txtWithEscapeSeq)
                log.info { "parse CodeFile:: \n[[\n${codeFile.getContent()}\n]]\n\n" }
                codeFiles.add(codeFile)
            } catch (ex: ParsingException) {
                log.info { "erro parisng file:: \n[[\n${txtWithEscapeSeq}\n]]\n is not java code \n" }
            }

        }

        assertTrue(codeFiles.isNotEmpty())

    }

}