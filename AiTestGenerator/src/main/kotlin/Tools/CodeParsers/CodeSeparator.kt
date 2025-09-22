package Tools.CodeParsers

data class CodePartIdicator(
    val characters: String,
    val detphAppend: String
)

data class CodeSeparator(
    val CodeSectionStart: String = "",
    val CodeSectionEnd: String = "",
    val bodyPartIndicator: CodePartIdicator = CodePartIdicator("", "")
) {
    fun increaseIndicator(): CodeSeparator {
        if (bodyPartIndicator == null) {
            return this
        }
        return CodeSeparator(
            CodeSectionStart,
            CodeSectionEnd,
            CodePartIdicator(
                bodyPartIndicator.characters + bodyPartIndicator.detphAppend,
                bodyPartIndicator.detphAppend
            )
        )
    }
}
