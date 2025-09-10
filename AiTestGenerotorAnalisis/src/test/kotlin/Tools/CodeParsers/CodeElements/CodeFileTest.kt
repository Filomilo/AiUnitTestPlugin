package Tools.CodeParsers.CodeElements

import Tools.CodeParsers.CodeParser
import org.filomilo.AiTestGenerator.Tools.CodeParsers.JavaParser
import org.filomilo.AiTestGenerator.Tools.StringTools
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CodeFileTest {

    fun methodsWithParentsProvider(): Stream<Arguments> {
        return Stream.of<Arguments>(
            Arguments.of(
                JavaParser,
                """
               package org.filomilo.AiTestGenerotorAnalisis;
        
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
""", listOf(
                    """
    public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }
}
    """,
                    """
    public class Calculator {

    public static int subtract(int a, int b) {
        return a - b;
    }
}
    """,
                    """
    public class Calculator {
    public static int multiply(int a, int b) {
        return a * b;
    }
}
    """,
                    """
    public class Calculator {
    public static int divide(int a, int b) {
        if (b == 0)
            throw new ArithmeticException("Divide by zero");
        return a / b;
    }
}
    """
                )
            )
        )
    }

    @MethodSource("methodsWithParentsProvider")
    @ParameterizedTest
    fun getMethodsWithParents(parser: CodeParser, javaCode: String, funcitonsWithParents: List<String>) {
        val javaCodeFile: CodeFile = parser.parseContent(javaCode)

        assertEquals(
            funcitonsWithParents.map { x -> StringTools.trimStringToJustSymbols(x) },
            javaCodeFile.getMethodsWithParents().map { x ->
                StringTools.trimStringToJustSymbols(
                    x.getContent(
                        JavaParser.getCodeSeparator()
                    )
                )
            })
    }

}