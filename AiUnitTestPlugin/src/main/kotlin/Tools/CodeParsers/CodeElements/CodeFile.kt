package Tools.CodeParsers.CodeElements

import java.nio.file.Path
import java.nio.file.Paths

abstract class CodeFile(
    open val dependecies: Set<String> = setOf(),
    open val codes: MutableList<Code> = mutableListOf()
) {


}
