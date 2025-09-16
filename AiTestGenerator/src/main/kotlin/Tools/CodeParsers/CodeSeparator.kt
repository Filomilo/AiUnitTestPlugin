package Tools.CodeParsers

data class CodeSeparator(
    val CodeSectionStart: String = "",
    val CodeSectionEnd: String = "",
    val bodyPartIndicator: String = ""
) {
    fun increaseIndicator(): CodeSeparator {
        return CodeSeparator(
            CodeSectionStart,
            CodeSectionEnd,
            bodyPartIndicator + bodyPartIndicator[0]
        )
    }
}
