package Tools.CodeParsers

import Tools.CodeParsers.ANTLR.JavaLexer
import Tools.CodeParsers.ANTLR.JavaParser
import Tools.CodeParsers.ANTLR.JavaParserBaseListener
import Tools.CodeParsers.CodeElements.JavaCodeFile
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.*
import java.nio.file.*


object JavaParser {

    fun parseJavaFile(path: Path): JavaCodeFile {
        TODO("Implement this function to complete the task")
    }

    fun parseFileContents(content: String): JavaCodeFile {
        val input: CharStream = CharStreams.fromString(content)
        val lexer: JavaLexer = JavaLexer(input)
        val tokens: CommonTokenStream = CommonTokenStream(lexer)
        val parser: JavaParser = JavaParser(tokens)
        val tree: JavaParser.CompilationUnitContext = parser.compilationUnit()
        val walker = ParseTreeWalker()
        walker.walk(object : JavaParserBaseListener() {
            override fun enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext) {
                System.out.println("Class found: ${ctx.CLASS().toString()}")
            }
        }, tree)
        TODO("Implement this function to complete the task")
    }

}