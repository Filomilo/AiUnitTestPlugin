package Projects

import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.PathResolver
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.ProjectsRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.nio.file.Path
import org.junit.jupiter.api.Assertions.*


class ProjectTest {

    @Test
    fun testClone() {
        var project: Project = ProjectsRepository.projects[0]
        var originalFiles: List<Path> = FilesManagment.getFilesRecursively(project.ProjectPath);
        var clonedPrject=project.clone(PathResolver.ensureTmpDirectory())
        var clonedFiles=FilesManagment.getFilesRecursively(clonedPrject.ProjectPath);
        var orgialFileNames:List<String> = originalFiles.stream().map { x->x.fileName.toString() }.toList()
        var copiedileNames:List<String> = clonedFiles.stream().map { x->x.fileName.toString() }.toList()
        assertEquals(orgialFileNames,copiedileNames)
    }

    @AfterEach
    fun removeTmpDirectory() {
//        FilesManagment.funRemoveDirectory(PathResolver.ensureTmpDirectory())
    }
}