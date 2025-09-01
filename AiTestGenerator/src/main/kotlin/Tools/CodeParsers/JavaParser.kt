package org.filomilo.AiTestGenerator.Tools.CodeParsers

import Tools.CodeParsers.ANTLR.JavaParser
import Tools.CodeParsers.ANTLR.JavaLexer
import Tools.CodeParsers.ANTLR.JavaParserBaseListener
import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeElements.JavaCodeFile
import Tools.CodeParsers.CodeParser
import Tools.CodeParsers.CodeSeparator
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.*
import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerotorAnalisis.Tools.CodeParsers.CodeElements.JavaClassBuilder
import org.filomilo.AiTestGenerotorAnalisis.Tools.CodeParsers.CodeElements.JavaCodeFileBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.*
import java.util.stream.Collectors


object JavaParser : CodeParser {
    private val log: Logger = LoggerFactory.getLogger(DockerConnection::class.java)


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


        var javaFileBuilder: JavaCodeFileBuilder = JavaCodeFileBuilder()
        var javaClassBuilder: JavaClassBuilder? = null

        walker.walk(object : JavaParserBaseListener() {

            override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext) {
                var pakcage: String = content.subSequence(
                    ctx.start.startIndex,
                    ctx.stop.stopIndex
                ).toString()
                log.info(
                    "enterPackageDeclaration: ${pakcage}"
                )

                javaFileBuilder.setPackageName(pakcage + ";")
            }


            override fun enterTypeDeclaration(ctx: JavaParser.TypeDeclarationContext) {
                if (ctx.classDeclaration() != null) {
                    val kind = ctx.classDeclaration().getStart().text
                    val name: String = ctx.classDeclaration().identifier().getText()
                    val modifiers =
                        ctx.classOrInterfaceModifier().stream().map { x -> x.text }.toList().joinToString(" ")
                    val fullDecl = "$modifiers $kind $name".trim { it <= ' ' }
                    if (javaClassBuilder != null) {
                        javaClassBuilder!!.finishClass()
                        javaClassBuilder = null;
                    }
                    javaClassBuilder = javaFileBuilder.addClass()
                    javaClassBuilder!!.setNameDeclaration(fullDecl)

                }

            }


            override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext) {
                log.info("enterImportDeclaration: ${ctx.text}")
                javaFileBuilder.addImport(ctx.text)

            }

            override fun enterClassBodyDeclaration(ctx: JavaParser.ClassBodyDeclarationContext) {
                if (ctx.memberDeclaration() != null && ctx.memberDeclaration().methodDeclaration() != null) {
                    val modifiers: String = ctx.modifier()
                        .stream()
                        .map { x -> x.text }
                        .collect(Collectors.joining(" "))

                    val m = ctx.memberDeclaration().methodDeclaration()

                    val returnType = m.typeTypeOrVoid().text
                    val name: String = m.identifier().getText()
                    val params = m.formalParameters().text
                    val start: Token = m.methodBody().getStart()
                    val stop: Token = m.methodBody().getStop()
                    val body = tokens.getText(start, stop)
                    val bodyTrim: String = body.subSequence(1, body.length - 1).toString()


                    val fullDecl = "$modifiers $returnType $name$params".trim { it <= ' ' }


                    javaClassBuilder!!
                        .addFunction()
                        .setDeclaration(fullDecl)
                        .setBody(bodyTrim)
                        .finish()

                }
            }


        }, tree)
        if (javaClassBuilder != null) {
            javaClassBuilder!!.finishClass()
            javaClassBuilder = null;
        }
        return javaFileBuilder.build();
    }

    override fun getCodeSeparator(): CodeSeparator {
        return CodeSeparator("{", "}")
    }

    override fun parseCodeFile(path: Path): CodeFile {

        var codeFlie: CodeFile = this.parseFileContents(Files.readString(path))
        codeFlie.file = path.toFile()
        return codeFlie
    }


}