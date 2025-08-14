package Tools.CodeParsers.CodeElements

import Tools.CodeParsers.CodeElements.CodeFile

class JavaCodeFile(
    val packageDelaration: String,
    override val dependecies: Set<String> = setOf(),
    override val codes: MutableList<Code> = mutableListOf()
) : CodeFile(dependecies, codes) {
    var packageDedlaration: String? = null;

    init {
        this.packageDedlaration = packageDelaration;
    }

    fun getContent(): String {
        val stringBuilder: StringBuilder = StringBuilder()
        stringBuilder.append(this.packageDelaration!! + "\n")
        stringBuilder.append(this.dependecies.joinToString("\n") + "\n")
        stringBuilder.append(
            this.codes.stream().map { x -> x.getContent("{\n", "}\n") }.toList().joinToString("\n") + "\n"
        )
        return stringBuilder.toString()
    }

}