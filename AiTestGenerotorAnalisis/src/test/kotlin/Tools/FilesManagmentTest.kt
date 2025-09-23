package Tools

import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerator.Tools.PathObject
import org.filomilo.AiTestGenerator.Tools.StringTools.toParsedString
import org.filomilo.AiTestGenerotorAnalisis.Projects.Project
import org.filomilo.AiTestGenerotorAnalisis.Projects.ProjectsRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FilesManagmentTest {
    @Test
    fun getFolderContent() {
        val paths: List<PathObject> = FilesManagment.getFolderContent(
            ProjectsRepository.projects[0].ProjectPath,
            ProjectsRepository.projects[0].ignoredPaths
        )
        assertNotNull(paths)
        assertEquals(2, paths.count())
        assertEquals(
            """
            -pom.xml
            -src
            --main
            ---java
            ----org
            -----filomilo
            ------AiTestGenerotorAnalisis
            -------Calculator.java
            --test
            ---java
            ----org
            -----filomilo
            ------AiTestGenerotorAnalisis
            -------CalculatorTest.java
            
        """.trimIndent(), paths.toParsedString()
        )
    }

}