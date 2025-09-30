package Projects.Runners

import Tools.CodeParsers.CodeElements.JavaCodeFile
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectsRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Path

class MavenRunnerTest {
    companion object {
        val log = LoggerFactory.getLogger(MavenRunnerTest.javaClass)

    }

    @Test
    fun getPathForTestFile() {
        val fileName = "testFile"
        val project: Project = ProjectsRepository.projects[0] // java proejct
        log.info("project apth: ${project.ProjectPath}")

        val javaCodeFile: JavaCodeFile = JavaCodeFile(
            packageDelaration = "",
            dependecies = emptySet(),
            codes = mutableListOf(Code("")),
        )
        javaCodeFile.file = File(fileName)

        val testFilePathExpected: Path =
            project.ProjectPath.resolve("src").resolve("test").resolve("java").resolve("$fileName.java")

        assertEquals(testFilePathExpected, project.getPathForTestFile(javaCodeFile))
    }

    @Test
    fun getPathForTestFolder() {
        val project: Project = ProjectsRepository.projects[0] // java proejct
        log.info("project apth: ${project.ProjectPath}")
        val expectedTestPath: Path = project.ProjectPath.resolve("src").resolve("test").resolve("java")
        assertEquals(expectedTestPath, project.getTestsPath())
    }

}