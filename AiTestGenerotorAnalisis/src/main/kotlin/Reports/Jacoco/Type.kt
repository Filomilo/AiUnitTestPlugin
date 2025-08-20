package org.filomilo.AiTestGenerotorAnalisis.Reports.Jacoco

enum class Type(val value: String) {
    BRANCH("BRANCH"),
    COMPLEXITY("COMPLEXITY"),
    INSTRUCTION("INSTRUCTION"),
    LINE("LINE"),
    METHOD("METHOD"),
    CLASS("CLASS");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "BRANCH"      -> BRANCH
            "COMPLEXITY"  -> COMPLEXITY
            "INSTRUCTION" -> INSTRUCTION
            "LINE"        -> LINE
            "METHOD"      -> METHOD
            "CLASS"       -> CLASS
            else          -> throw IllegalArgumentException()
        }
    }
}