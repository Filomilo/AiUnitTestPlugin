package org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements

import Tools.CodeParsers.CodeSeparator

data class Code(var code: String? = null, var children: List<Code> = listOf(), var parent: Code? = null) {

    constructor(code: String) : this(code, listOf())


    fun getContent(codeSeparator: CodeSeparator): String {
        var StringBuilder: StringBuilder = StringBuilder("")
        val split: List<String> =
            code!!.split("\n")
        val join = split.joinToString("\n")
        StringBuilder.append(join)

        if (children.isNotEmpty()) {
            StringBuilder.append(codeSeparator.CodeSectionStart)
            for (child in children) {
                StringBuilder.append(codeSeparator.bodyPartIndicator)
                StringBuilder.append(child.getContent(codeSeparator.increaseIndicator()))
            }
            StringBuilder.append(codeSeparator.CodeSectionEnd)
        }
        StringBuilder.append("\n")
        return StringBuilder.toString()
    }


}