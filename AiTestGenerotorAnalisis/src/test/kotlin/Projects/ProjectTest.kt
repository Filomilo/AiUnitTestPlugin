package Projects

import org.filomilo.AiTestGenerator.LLM.Containers.Docker.DockerConnection
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.Tools.PathResolver
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectsRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.stream.Stream


class ProjectTest {
    @Test
    fun clone() {

    }

    private val log: Logger = LoggerFactory.getLogger(ProjectTest::class.java)


    companion object {
        @JvmStatic
        fun projectProvider(): Stream<Project> = ProjectsRepository.projects.stream()
    }

    @Test
    fun testClone() {
        var project: Project = ProjectsRepository.projects[0]
        var originalFiles: List<Path> = FilesManagment.getFilesRecursively(project.ProjectPath);
        var clonedPrject = project.clone(PathResolver.ensureTmpDirectory().resolve(project.name))
        var clonedFiles = FilesManagment.getFilesRecursively(clonedPrject.ProjectPath);
        var orgialFileNames: List<String> = originalFiles.stream().map { x -> x.fileName.toString() }.toList()
        var copiedileNames: List<String> = clonedFiles.stream().map { x -> x.fileName.toString() }.toList()
        clonedPrject.destroy()
        assertEquals(orgialFileNames, copiedileNames)

    }

    @Test
    fun testDestroy() {
        FilesManagment.deleteContentOfFolder(PathResolver.ensureTmpDirectory())
        assertEquals(0, FilesManagment.getFilesRecursively(PathResolver.ensureTmpDirectory()).size)
        var project: Project = ProjectsRepository.projects[0]
        var clonedPrject = project.clone(PathResolver.ensureTmpDirectory().resolve(project.name))
        assertTrue(FilesManagment.getFilesRecursively(PathResolver.ensureTmpDirectory().resolve(project.name)).size > 0)
        clonedPrject.destroy()
        assertEquals(0, FilesManagment.getFilesRecursively(PathResolver.ensureTmpDirectory()).size)


    }

    @AfterEach
    fun removeTmpDirectory() {
        FilesManagment.funRemoveDirectory(PathResolver.ensureTmpDirectory())
    }

    @ParameterizedTest
    @MethodSource("projectProvider")
    fun runTests(project: Project) {
        log.info("running tests on project ${project.name}")
        assertDoesNotThrow {
            project.runTests()
        }

    }

    @ParameterizedTest
    @MethodSource("projectProvider")
    fun extractReport(project: Project) {
        runTests(project)
        assertDoesNotThrow {
            project.getReport()
        }

    }
}