package Tools.CodeParsers

import Tools.CodeParsers.CodeElements.CodeFile
import java.io.File
import java.nio.file.Path

interface CodeParser {
    fun getCodeSeparator(): CodeSeparator
    fun parseCodeFile(path: Path): CodeFile
    fun parseContent(content: String): CodeFile
    fun getLanguage(): String
    fun createCodeFile(
        dependecies: kotlin.collections.List<String>,
        codes: kotlin.collections.MutableList<String>,
        file: java.io.File
    ): CodeFile
}