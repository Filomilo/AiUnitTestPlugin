package org.filomilo.AiTestGenerator.Tools

import Tools.PathResolver
import java.lang.StringBuilder

object StringTools {
    public fun trimStringToJustSymbols(a: String): String {
        return a.replace(Regex("\\s+"), " ").replace("\r\n", "").replace("\n", "")
            .replace("\t", "").replace(" ", "")
            .replace(Regex("\\s+"), " ");
    }

    public fun turnCharsIntoEscapeSequance(text: String): String {
        return text.replace("\\n", "\n")
            .replace("\\t", "\t")
            .replace("\\\"", "\"")
    }

    fun turnEscapreSeqanceIntoChars(text: String): String {
        return text.replace("\n", "\\n")
            .replace("\t", "\\t")
            .replace("\"", "\\\"")
    }

    private fun parsePathObjectChildren(paths: List<PathObject>, depth: Int, stringBuilder: StringBuilder) {
        for (path in paths) {
            for (i in 0..depth) {
                stringBuilder.append("-")
            }
            stringBuilder.append(path.name)
            stringBuilder.append("\n")
            parsePathObjectChildren(path.children, depth + 1, stringBuilder)
        }


    }

    fun List<PathObject>.toParsedString(): String {
        val stringBuilder: StringBuilder = StringBuilder()
        parsePathObjectChildren(this, 0, stringBuilder)
        return stringBuilder.toString()
    }


}