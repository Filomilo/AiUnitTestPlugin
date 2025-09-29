package Tools.CodeParsers.CodeElements

import Tools.CodeParsers.CodeSeparator
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import java.io.File


class JavaCodeFile(
    val packageDelaration: String?,
    override val dependecies: Set<String> = setOf(),
    override val codes: MutableList<Code> = mutableListOf(),
    file: File? = null
) : CodeFile(dependecies, codes, file) {
    var packageDedlaration: String? = null;

    init {
        this.packageDedlaration = packageDelaration;
    }

    override fun getContent(): String {
        val stringBuilder: StringBuilder = StringBuilder()
        this.packageDelaration?.let {
            stringBuilder.append(this.packageDelaration!! + "\n")
        }

        stringBuilder.append(this.dependecies.joinToString("\n") + "\n")
        stringBuilder.append(
            this.codes.stream().map { x -> x.getContent(CodeSeparator("{\n", "}\n")) }.toList()
                .joinToString("\n") + "\n"
        )
        return stringBuilder.toString()
    }

    override fun getMethods(): Collection<Code> {
        return codes.map { x -> x.children }.flatten()
    }


}