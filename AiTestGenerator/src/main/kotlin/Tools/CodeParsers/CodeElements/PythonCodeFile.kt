package Tools.CodeParsers.CodeElements

import Tools.CodeParsers.CodePartIdicator
import Tools.CodeParsers.CodeSeparator
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import java.io.File

class PythonCodeFile(
    dependecies: Set<String> = setOf(),
    codes: MutableList<Code> = mutableListOf(),
    file: File? = null
) : CodeFile(dependecies, codes, file) {
    override fun getMethods(): Collection<Code> {
        return codes.map { x -> x.children }.flatten()
    }

    override fun getContent(): String {
        val stringBuilder: StringBuilder = StringBuilder()

        stringBuilder.append(this.dependecies.joinToString("\n") + "\n")
        stringBuilder.append(
            this.codes.stream().map { x -> x.getContent(CodeSeparator(":\n", "", CodePartIdicator("    ", "    "))) }
                .toList()
                .joinToString("\n") + "\n"
        )
        return stringBuilder.toString()
    }
}