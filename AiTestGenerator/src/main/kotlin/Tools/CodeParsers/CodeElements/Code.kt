package org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements

import Tools.CodeParsers.CodeSeparator

data class Code(var code: String? = null, var children: List<Code> = listOf(), var parent: Code? = null) {

    constructor(code: String) : this(code, listOf())


    fun getContent(codeSeparator: CodeSeparator): String {
        var StringBuilder: StringBuilder = StringBuilder("")
        StringBuilder.append(code)
        if (children.isNotEmpty()) {
            StringBuilder.append(codeSeparator.CodeSectionStart)
            for (child in children) {
                StringBuilder.append(child.getContent(codeSeparator))
            }
            StringBuilder.append(codeSeparator.CodeSectionEnd)
        }

        return StringBuilder.toString()
    }


}