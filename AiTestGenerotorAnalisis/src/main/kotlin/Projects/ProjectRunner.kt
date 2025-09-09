package org.filomilo.AiTestGenerotorAnalisis.Projects

import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeParser
import java.nio.file.Path

interface ProjectRunner {
    fun runTests(projectPath: Path)
    fun getPathForTestFile(codeFile: CodeFile, projectPath: Path): Path
    fun getPathForTestFolder(projectPath: Path): Path
}