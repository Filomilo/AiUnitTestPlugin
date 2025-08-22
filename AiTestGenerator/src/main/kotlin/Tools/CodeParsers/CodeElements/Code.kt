package org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements

class Code(code: String, children: List<Code>) {

    constructor(code: String) : this(code, listOf())

    var code: String = code
    var children: List<Code> = children


    fun getContent(serpratorStart: String, serpratorEnd: String = ""): String {
        var StringBuilder: StringBuilder = StringBuilder("")
        StringBuilder.append(code)
        if (children.isNotEmpty()) {
            StringBuilder.append(serpratorStart)
            for (child in children) {
                StringBuilder.append(child.getContent(serpratorStart, serpratorEnd))
            }
            StringBuilder.append(serpratorEnd)
        }

        return StringBuilder.toString()
    }
}