package org.filomilo.AiTestGenerator.Tools

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
}