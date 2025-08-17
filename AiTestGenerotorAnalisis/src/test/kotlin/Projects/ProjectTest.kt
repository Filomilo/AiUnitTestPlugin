package Projects

import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.ProjectsRepository
import org.junit.jupiter.api.Test
import java.nio.file.Path


class ProjectTest {

    @Test
    fun testClone() {
        var project: Project = ProjectsRepository.projects[0]
        var originalFiles: List<Path> = FilesManagment.getFilesRecursively(project.ProjectPath);
    }
}