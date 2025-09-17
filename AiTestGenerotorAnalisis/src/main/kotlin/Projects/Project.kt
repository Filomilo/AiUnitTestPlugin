package org.filomilo.AiTestGenerotorAnalisis.Projects

import Tools.CodeParsers.CodeElements.CodeFile
import Tools.CodeParsers.CodeElements.JavaCodeFile
import Tools.CodeParsers.CodeParser
import Tools.CodeParsers.ParsingException
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.ReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import org.slf4j.LoggerFactory
import java.io.File
import java.io.Serializable
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.exists
import kotlin.io.path.extension

data class Project(
    val name: String,
    val ProjectPath: Path,
    val codeParser: CodeParser,
    private val projectRunner: ProjectRunner,
    private val reportExtractor: ReportExtractor,
    val testingFramework: String,
    val codeFileExtension: String,
    val ignoredFiles: List<Path> = emptyList()

) :
    Serializable {
    companion object {
        val log = LoggerFactory.getLogger(Project.javaClass)
    }

    fun getTestsPath(): Path {
        return projectRunner.getPathForTestFolder(this.ProjectPath)
    }

    fun clone(newPath: Path): Project {
        if (newPath.exists()) {
            FilesManagment.deleteDirecotry(newPath)
        }
        FilesManagment.copyDirectory(this.ProjectPath, newPath)

        return Project(
            this.name,
            newPath,
            this.codeParser,
            this.projectRunner,
            this.reportExtractor,
            this.testingFramework,
            this.codeFileExtension,
            this.ignoredFiles
        )
    }

    fun runTests(): String {
        return projectRunner.runTests(this.ProjectPath)
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
        var files: Collection<CodeFile> = mutableListOf()
        for (file in getAllCodeFiles()) {
            try {
                files += this.codeParser.parseCodeFile(
                    file
                )
            } catch (ex: ParsingException) {
                log.warn("faile to parse file $file :: ${ex.message}")
            }
        }
        if (files.isEmpty()) {
            throw ParsingException("no elgible files found")
        }
        return files
    }

    fun getAllMethods(): Collection<Code> {
        return getAllParsedFiles().stream().map { x -> x.getMethods() }.collect(Collectors.toList()).flatten()
    }

    fun reolvePathToTestCodeFile(x: CodeFile): Path {
        return this.projectRunner.getPathForTestFile(x, this.ProjectPath)
    }

    fun clearTests() {
        FilesManagment.deleteContentOfFolder(this.projectRunner.getPathForTestFolder(this.ProjectPath))
        FilesManagment.deleteFilse(this.reportExtractor.getReportFiles(this.ProjectPath))
    }

    fun destroy() {
        FilesManagment.deleteDirecotry(this.ProjectPath)
    }

    fun getPathForTestFile(javaCodeFile: JavaCodeFile): Path {
        return getTestsPath().resolve(javaCodeFile.file?.name + ".$codeFileExtension")
    }

    fun getAllMethodsWithParents(): Collection<Code> {
        return getAllParsedFiles().stream().map { x -> x.getMethodsWithParents() }.collect(Collectors.toList())
            .flatten()
    }

    fun getTestFiles(): List<Path> {
        return FilesManagment.getFilesRecursively(getTestsPath()).filter { x -> x.extension == this.codeFileExtension }
            .toList()

    }

    fun getLanguage(): String {
        return codeParser.getLanguage()
    }


}
