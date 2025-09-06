package org.filomilo.AiTestGenerotorAnalisis.Tools


import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.exists
import kotlin.io.path.notExists

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

        val proejctDir: Path = Paths.get(System.getProperty("user.dir")).parent

        val ProjectPath: Path =
            proejctDir.toAbsolutePath().resolve("ExampleProjects").resolve(projectName)
        if (!ProjectPath.exists()) {
            throw IllegalArgumentException("Invalid parth provided there is no folder of name $projectName in exampleProjectFolder :: Path resolved to \n ${ProjectPath.toAbsolutePath()}")
        }
        return ProjectPath
    }

    fun resolveTmpFolder(folderName: String): Path {
        var path = ensureTmpDirectory().resolve(folderName)
        return path
    }

    fun getResultFilePath(): Path {
        val proejctDir: Path = Paths.get(System.getProperty("user.dir")).parent
        val folderPath: Path =
            proejctDir.toAbsolutePath().resolve("Analysis_results")
        if (!folderPath.exists()) {
            Files.createDirectories(folderPath)
        }
        val filePath: Path = folderPath.resolve("result.json")

        return filePath
    }

    fun ensureUniqeIncramentalFileName(basePath: Path, fileName: String): Path {
        var path: Path = basePath.resolve(fileName)
        val originalpath: Path = basePath.resolve(fileName)
        if (path.notExists()) {
            return path
        }
        var i = 0
        while (path.exists()) {
            i++
            var file: File = originalpath.toFile()
            var newName = file.nameWithoutExtension + "_$i." + file.extension
            path = basePath.resolve(newName)
        }
        return path
    }
}