package LLM.JsonResponses

import kotlinx.serialization.Serializable

@Serializable
data class FileNameImportContentAndContent(
    val file_name: String,
    val file_content: String,
    val imports: List<String>
) : JsonResponse {
    companion object {
        fun getEmpty(): JsonResponse {
            return FileNameImportContentAndContent(
                "", "", emptyList()
            )
        }
    }

    override fun getJsonFormat(): String {
        return """
       {
        "type": "object",
        "properties": {
            "file_name": {
                "type": "string",
                "description": "File name and extenstion to be file created with tests, must end with .py"
            },
            "file_content": {
                "type": "string",
                "description": "Content of file containing unit tests"
            },
            "imports": {
                "type": "array",
                "items": {
                    "type": "string",
                    "description": "import line for dependecies used in unit tests in file and code tested "
                }
            }
        },
        "required": [
            "file_name",
            "imports",
            "file_content"
        ]
    }
   """.trimIndent()
    }


}