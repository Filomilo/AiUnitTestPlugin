package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeParser
import org.filomilo.AiTestGenerotorAnalisis.Tools.CommandExecutor
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectRunner
import Tools.PathResolver
import java.nio.file.Path
import kotlin.io.path.exists

class MavenRunner() : ProjectRunner {


    override fun runTests(projectPath: Path) {
        CommandExecutor.runCommand("mvn clean install", projectPath)
    }

    override fun getPathForTestFile(
        codeFile: CodeFile,
        projectPath: Path
    ): Path {
        val basePath: Path = projectPath.resolve("src").resolve("test").resolve("java")
        val fileName: String = codeFile.file?.name + ".java"
        var path: Path = PathResolver.ensureUniqeIncramentalFileName(basePath, fileName)

        return path
    }


    override fun getPathForTestFolder(projectPath: Path): Path {
        return projectPath.resolve("src").resolve("test").resolve("java")
    }


}