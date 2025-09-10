package Tools.CodeParsers.CodeElements

import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import java.nio.file.Path
import java.nio.file.Paths

abstract class CodeFile(
    open val dependecies: Set<String> = setOf(),
    open val codes: MutableList<Code> = mutableListOf()
) {


}
