package Tools.CodeParsers.CodeElements

import java.nio.file.Path
import java.nio.file.Paths

abstract class CodeFile(
    val path: Path = Paths.get(""),
    val dependecies: MutableList<String> = mutableListOf(),
    val codes: MutableList<Code> = mutableListOf()
) {


    constructor(filename: String) : this(Paths.get(filename))


}
