package Tools.CodeParsers

import Tools.CodeParsers.ANTLR.JavaLexer
import Tools.CodeParsers.ANTLR.JavaParser
import Tools.CodeParsers.ANTLR.JavaParserBaseListener
import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeElements.JavaCodeFile
import Tools.CodeParsers.CodeElements.PythonCodeFile
import java.nio.file.Path
import Tools.CodeParsers.ANTLR.Python3Parser
import Tools.CodeParsers.ANTLR.Python3Lexer
import Tools.CodeParsers.ANTLR.Python3ParserBaseListener
import Tools.CodeParsers.CodeElements.PythonClassBuilder
import Tools.CodeParsers.CodeElements.PythonCodeFileBuilder
import Tools.CodeParsers.CodeElements.PythonFunctionBuilder
import com.sun.xml.bind.v2.TODO
import io.ktor.util.reflect.instanceOf

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerotorAnalisis.Tools.CodeParsers.CodeElements.JavaClassBuilder
import org.filomilo.AiTestGenerotorAnalisis.Tools.CodeParsers.CodeElements.JavaCodeFileBuilder
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.util.stream.Collectors

class PythonParser : CodeParser {

    companion object {
        val log = LoggerFactory.getLogger(PythonParser.javaClass)
    }

    private fun parseFileContents(content: String): PythonCodeFile {
        val input: CharStream = CharStreams.fromString(content)
        val lexer: Python3Lexer = Python3Lexer(input)
        val tokens: CommonTokenStream = CommonTokenStream(lexer)
        val parser: Python3Parser = Python3Parser(tokens)
        val tree = parser.file_input()
        val walker = ParseTreeWalker()


        var pythonfileBuilder: PythonCodeFileBuilder = PythonCodeFileBuilder()
        var pythonClassBuilder: PythonClassBuilder? = null

        walker.walk(object : Python3ParserBaseListener() {


            override fun enterClassdef(ctx: Python3Parser.ClassdefContext) {

                val startIndex: Int = ctx.start.startIndex
                val stopIndex: Int = ctx.start.stopIndex

                val classdefinitonLine = "class ${ctx.name().text}"
                pythonClassBuilder = pythonfileBuilder.addClass()
                pythonClassBuilder?.setNameDeclaration(classdefinitonLine)


                log.info("calss: $classdefinitonLine")

            }

            override fun exitClassdef(ctx: Python3Parser.ClassdefContext?) {
                if (pythonClassBuilder != null) {
                    pythonClassBuilder?.finishClass()
                    pythonClassBuilder = null
                }
            }


            override fun enterFuncdef(ctx: Python3Parser.FuncdefContext) {
                val defintionStartInxes = ctx.start.startIndex
                val definitionStopIndex = ctx.stop.stopIndex
                val defintion = content.substring(defintionStartInxes, definitionStopIndex + 1)
                val name = ctx.name().text
                val params = ctx.parameters().text
                val returnType = if (ctx.test() != null) " -> ${ctx.test().text}" else ""
                val defHeader = "def $name$params$returnType"
                val bodyStart: Token = ctx.block().start
                val bodyStop: Token = ctx.block().stop
                val startIdx = bodyStart.startIndex
                val stopIdx = bodyStop.stopIndex
                val indent: Int = ctx.block().INDENT().text.length
                val body = content.substring(startIdx + 1, stopIdx + 1)
                val lines: List<String> = body.split('\n')
                val linesindentd: MutableList<String> = mutableListOf(lines[0])
                linesindentd.addAll(
                    lines.stream().filter { x -> x.isNotBlank() }.skip(1).map<String> { line: String ->
                        line.substring(indent)
                    }.toList()
                )
                val bodyParsed = linesindentd.joinToString("\n")
//                val body: String = tokens.getText(bodyStart, bodyStop)

                log.info("enter func def: $defHeader")
                log.info("body: $body")

//                var bodyTrimmed = body
//                if (bodyTrimmed.startsWith(":")) {
//                    bodyTrimmed = bodyTrimmed.substring(1).trim()
//                }
                log.info(ctx.ruleContext.toString())

                val functionBuilder: PythonFunctionBuilder =
                    if (pythonClassBuilder != null) {
                        pythonClassBuilder!!.addFunction()
                    } else {
                        pythonClassBuilder?.finishClass()
                        pythonClassBuilder = null
                        pythonfileBuilder.addFunction()
                    }



                functionBuilder
                    .setDeclaration(defHeader)
                    .setBody(bodyParsed)
                    .finish()
            }


            override fun enterImport_stmt(ctx: Python3Parser.Import_stmtContext) {
                var start: Int = ctx.start.startIndex
                var stop: Int = ctx.stop.stopIndex + 1
                var import: String = content.substring(start, stop)
                pythonfileBuilder.addImport(import)

//                val importLine = ctx.text
//                log.info("import: $importLine")
            }


            override fun enterImport_from(ctx: Python3Parser.Import_fromContext) {

                val fromLine = content.substring(ctx.start.startIndex, ctx.stop.stopIndex + 1)
                pythonfileBuilder.addImport(fromLine)

            }

            override fun enterSimple_stmt(ctx: Python3Parser.Simple_stmtContext) {

                if (ctx.import_stmt() != null) return

                val start = ctx.start.startIndex
                val stop = ctx.stop.stopIndex + 1
                val code = content.substring(start, stop)


                val inClass = ctx.findAncestor<Python3Parser.ClassdefContext>() != null
                val inFunc = ctx.findAncestor<Python3Parser.FuncdefContext>() != null


                if (!inClass && !inFunc) {
                    pythonfileBuilder.addLine(Code(code.trim()))
                    log.info("Top-level statement: $code")
                }
            }


        }, tree)

        return pythonfileBuilder.build()
    }

    override fun getCodeSeparator(): CodeSeparator {
        return CodeSeparator(CodeSectionStart = ":\n", bodyPartIndicator = "\t")
    }

    override fun parseCodeFile(path: Path): CodeFile {
        var codeFlie: CodeFile = this.parseFileContents(Files.readString(path))
        codeFlie.file = path.toFile()
        return codeFlie
    }

    override fun parseContent(content: String): CodeFile {
        return parseFileContents(content)
    }
}

inline fun <reified T : ParserRuleContext> ParserRuleContext.findAncestor(): T? {
    var current: RuleContext? = this.parent
    while (current != null) {
        if (current is T) {
            return current
        }
        current = current.parent
    }
    return null
}