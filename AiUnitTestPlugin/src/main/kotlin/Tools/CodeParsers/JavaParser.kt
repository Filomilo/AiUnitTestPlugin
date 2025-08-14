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
        var packageName: String? = null
        var imports: MutableList<String> = ArrayList()
        var className: String? = null
        var fields: MutableList<String> = ArrayList()
        var methods: MutableList<String> = ArrayList()
        walker.walk(object : JavaParserBaseListener() {

            override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext) {
                packageName = ctx.text
            }

            override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext) {
                imports.add(ctx.text)
            }

            override fun enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext) {
                className = ctx.identifier().getText()
            }

            override fun enterFieldDeclaration(ctx: JavaParser.FieldDeclarationContext) {
                fields.add(ctx.text + ";")
            }

            override fun enterMethodDeclaration(ctx: JavaParser.MethodDeclarationContext) {
                methods.add(ctx.text)
            }
        }, tree)

        TODO("Implement this function to complete the task")
    }

}