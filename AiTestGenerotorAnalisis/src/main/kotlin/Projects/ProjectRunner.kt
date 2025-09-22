package org.filomilo.AiTestGenerotorAnalisis.Projects

import Tools.CodeParsers.CodeElements.CodeFile
import java.nio.file.Path

interface ProjectRunner {
    fun runTests(projectPath: Path): String
    fun getPathForTestFile(codeFile: CodeFile, projectPath: Path): Path
    fun getPathForTestFolder(projectPath: Path): Path
    fun clearFiles(project: Project)
}