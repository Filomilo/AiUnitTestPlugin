package org.filomilo.AiTestGenerotorAnalisis.Projects

import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeParser
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.ReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import java.io.File
import java.io.Serializable
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

data class Project(
    val name: String,
    val ProjectPath: Path,
    val codeParser: CodeParser,
    private val projectRunner: ProjectRunner,
    private val reportExtractor: ReportExtractor,
    val testingFramework: String,
    val codeFileExtension: String

) :
    Serializable {

    fun clone(newPath: Path): Project {
        FilesManagment.copyDirectory(this.ProjectPath, newPath)

        return Project(
            this.name,
            newPath,
            this.codeParser,
            this.projectRunner,
            this.reportExtractor,
            this.testingFramework,
            this.codeFileExtension
        )
    }

    fun runTests() {
        projectRunner.runTests(this.ProjectPath)
    }

    fun getReport(): TestReport {
        return reportExtractor.extractReport(this.ProjectPath)
    }

    fun getAllCodeFiles(): Collection<Path> {
        return FilesManagment.findFilesByExtensions(
            this.ProjectPath,
            listOf(this.codeFileExtension)
        ).toCollection(ArrayList())
    }


    fun getAllParsedFiles(): Collection<CodeFile> {

        return getAllCodeFiles().map { x ->
            this.codeParser.parseCodeFile(
                x
            )
        }.toCollection(ArrayList());
    }

    fun getAllMethods(): Collection<Code> {
        return getAllParsedFiles().stream().map { x -> x.getMethods() }.collect(Collectors.toList()).flatten()
    }


}
