package org.filomilo.AiTestGenerotorAnalisis.Projects

import Projects.ProjectTypes
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.ProjectRunner
import java.io.Serializable
import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

data class Project(val name: String,
                   val ProjectPath: Path,
                   val projectType: ProjectTypes,
                    val projectRunner: ProjectRunner

) :
    Serializable {

    fun clone(newPath: Path): Project {
        FilesManagment.copyDirectory(this.ProjectPath,newPath)

        return Project(
            this.name,
            newPath,
            this.projectType,
            this.projectRunner
        )
    }

    fun runTests(){
        projectRunner.runTests(this.ProjectPath)
    }

}
