package Tools.CodeParsers

import Tools.CodeParsers.CodeElements.JavaCodeFile
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.nio.file.Paths

class JavaParserTest {


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
        return a.replace("\\s+".toRegex(), "")
    }

    @Test
    fun parseJavaFile() {
    }

    @Test
    fun parseFileContents() {
        var JavaCodeFile: JavaCodeFile = JavaParser.parseFileContents(this.testFileConent_Calculator)
        assertEquals(listOf("package org.filomilo.AiTestGenerotorAnalisis;"), JavaCodeFile.dependecies)
        assertEquals(Paths.get("package/org/filomilo/AiTestGenerotorAnalisis.java"), JavaCodeFile.path)
        assertEquals(1, JavaCodeFile.codes.size)
    }
}