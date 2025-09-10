package CodeParsers.CodeElements

import Tools.CodeParsers.CodeElements.JavaCodeFile
import org.filomilo.AiTestGenerotorAnalisis.Tools.CodeParsers.CodeElements.JavaCodeFileBuilder
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class JavaCodeFileBuilderTest {


    val testFileConent_Calculator: String = """package org.filomilo.AiTestGenerotorAnalisis;
        
        import java.io.Example;

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
}
"""

    @Test
    fun build() {
        var javaCodeFile: JavaCodeFile = JavaCodeFileBuilder()
            .addImport("import java.io.Example;")
            .setPackageName("package org.filomilo.AiTestGenerotorAnalisis;")
            .addClass()
            .setNameDeclaration("public class Calculator")
            .addFunction()
            .setDeclaration("public static int add(int a, int b)")
            .setBody("return a + b;")
            .finish()

            .addFunction()
            .setDeclaration("public static int subtract(int a, int b)")
            .setBody("return a - b;")
            .finish()


            .addFunction()
            .setDeclaration("public static int multiply(int a, int b)")
            .setBody("return a * b;")
            .finish()


            .addFunction()
            .setDeclaration("public static int divide(int a, int b)")
            .setBody(
                "  if (b == 0)\n" +
                        "            throw new ArithmeticException(\"Divide by zero\");\n" +
                        "        return a / b;"
            )
            .finish()


            .finishClass()
            .build()
        var classFileConetnt: String = javaCodeFile.getContent()
        assertEquals(
            testFileConent_Calculator.replace(Regex("\\s+"), " ").replace("\r\n", "").replace("\n", "")
                .replace("\t", "").replace(" ", "")
                .replace(Regex("\\s+"), " "),
            classFileConetnt.replace(Regex("\\s+"), " ").replace("\r\n", "").replace("\n", "").replace("\t", "")
                .replace(Regex("\\s+"), " ").replace(" ", "")
        )
    }
}