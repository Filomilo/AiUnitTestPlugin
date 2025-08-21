package org.filomilo.AiTestGenerotorAnalisis.Projects

import Projects.ProjectTypes
import org.filomilo.AiTestGenerator.Tools.FilesManagment
import org.filomilo.AiTestGenerotorAnalisis.ProjectRunner
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.ReportExtractor
import org.filomilo.AiTestGenerotorAnalisis.Projects.Reports.TestReport
import java.io.Serializable
import java.nio.file.Path

data class Project(val name: String,
                   val ProjectPath: Path,
                   val projectType: ProjectTypes,
                   private   val projectRunner: ProjectRunner,
                private val reportExtractor: ReportExtractor

) :
    Serializable {

    fun clone(newPath: Path): Project {
        FilesManagment.copyDirectory(this.ProjectPath,newPath)

        return Project(
            this.name,
            newPath,
            this.projectType,
            this.projectRunner,
            this.reportExtractor
        )
    }

    fun runTests(){
        projectRunner.runTests(this.ProjectPath)
    }
    fun getReport ():TestReport{
       return reportExtractor.extractReport(this.ProjectPath)
    }
}
