package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CommandExecutor
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectRunner
import Tools.PathResolver
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import java.nio.file.Files
import java.nio.file.Path

class PythonRunner(val projectName: String) : ProjectRunner {
    override fun runTests(projectPath: Path): String {
        val result: String =
            CommandExecutor.runCommand(
                " pytest tests/ --cov=${projectName} --continue-on-collection-errors --cov-report json\n",
                projectPath
            )
        return result
    }

    override fun getPathForTestFile(
        codeFile: CodeFile,
        projectPath: Path
    ): Path {
        val basePath: Path = getPathForTestFolder(projectPath)
        val fileName: String = codeFile.file?.name + ".py"
        var path: Path = PathResolver.ensureUniqeIncramentalFileName(basePath, fileName)

        return path
    }


    override fun getPathForTestFolder(projectPath: Path): Path {
        return projectPath.resolve("tests")
    }

    override fun clearFiles(project: Project) {
        FilesManagment.deleteContentOfFolder(this.getPathForTestFolder(project.ProjectPath))
        Files.writeString (this.getPathForTestFolder(project.ProjectPath).resolve("__init__.py"), "")
    }

}