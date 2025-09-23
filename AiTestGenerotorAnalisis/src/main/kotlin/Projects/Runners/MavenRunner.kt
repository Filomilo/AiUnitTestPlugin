package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CommandExecutor
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectRunner
import Tools.PathResolver
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import java.nio.file.Path

class MavenRunner() : ProjectRunner {


    override fun runTests(projectPath: Path): String {
        return CommandExecutor.runCommand("mvn clean install", projectPath)
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

    override fun clearFiles(project: Project) {
        FilesManagment.deleteContentOfFolder(this.getPathForTestFolder(project.ProjectPath))

    }


}