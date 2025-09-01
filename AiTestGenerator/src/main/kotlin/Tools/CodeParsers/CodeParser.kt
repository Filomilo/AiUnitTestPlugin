package Tools.CodeParsers

import Tools.CodeParsers.CodeElements.CodeFile
import java.nio.file.Path

interface CodeParser {
    fun getCodeSeparator(): CodeSeparator
    fun parseCodeFile(path: Path): CodeFile
}