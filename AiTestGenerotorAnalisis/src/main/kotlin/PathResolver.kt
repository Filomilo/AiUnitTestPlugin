package org.filomilo.AiTestGenerotorAnalisis

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.exists

object PathResolver {


    fun ensureTmpDirectory(): Path {
        val ProjectTmpPath: Path =
            Paths.get("").toAbsolutePath().parent.resolve("tmp")
        if (!ProjectTmpPath.exists()) {
            Files.createDirectories(ProjectTmpPath)
        }
        return ProjectTmpPath
    }


    fun resolveExampleProject(projectName: String): Path {
        val ProjectPath: Path =
            Paths.get("").toAbsolutePath().parent.resolve("ExampleProjects").resolve(projectName)
        if (!ProjectPath.exists()) {
            throw IllegalArgumentException("Invalid parth provided there is no folder of name $projectName in exampleProjectFolder :: Path resolved to \n ${ProjectPath.toAbsolutePath()}")
        }
        return ProjectPath
    }

    fun resolveTmpFolder(folderName: String): Path {
        var path = resolveTmpFolder(folderName)
        return path
    }
}