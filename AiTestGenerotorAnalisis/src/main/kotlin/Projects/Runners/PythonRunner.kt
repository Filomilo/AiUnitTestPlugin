package org.filomilo.AiTestGenerotorAnalisis.Projects.Runners

import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeParser
import org.filomilo.AiTestGenerotorAnalisis.Tools.CommandExecutor
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectRunner
import org.filomilo.AiTestGenerotorAnalisis.Tools.PathResolver
import java.nio.file.Path

class PythonRunner(val projectName: String) : ProjectRunner {
    override fun runTests(projectPath: Path) {
        CommandExecutor.runCommand(" pytest tests/ --cov=${projectName} --cov-report json\n", projectPath)
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
}