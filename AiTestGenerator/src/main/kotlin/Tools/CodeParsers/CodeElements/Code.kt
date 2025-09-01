package org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements

import Tools.CodeParsers.CodeSeparator

class Code(code: String, children: List<Code>) {

    constructor(code: String) : this(code, listOf())

    var code: String = code
    var children: List<Code> = children


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