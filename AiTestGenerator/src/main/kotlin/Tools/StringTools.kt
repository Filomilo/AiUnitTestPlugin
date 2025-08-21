package org.filomilo.AiTestGenerator.Tools

object StringTools {
    public fun trimStringToJustSymbols(a: String): String {
        return a.replace(Regex("\\s+"), " ").replace("\r\n", "").replace("\n", "")
            .replace("\t", "").replace(" ", "")
            .replace(Regex("\\s+"), " ");
    }
}