package org.filomilo.AiTestGenerotorAnalisis

import java.io.FileNotFoundException
import java.io.Serializable
import java.nio.file.InvalidPathException
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.exists

data class ProjectConfig(val name: String, val exampleProjectFolder: String, val projectType: ProjectTypes) :
    Serializable {

    val ProjectPath: Path =
        Paths.get("").toAbsolutePath().parent.resolve("ExampleProjects").resolve(exampleProjectFolder)

    init {
        if (!ProjectPath.exists()) {
            throw IllegalArgumentException("Invalid parth provided there is no folder of name $exampleProjectFolder in exampleProjectFolder :: Path resolved to \n ${ProjectPath.toAbsolutePath()}")
        }
    }

}
