package org.filomilo.AiTestGenerator.Tools

import java.nio.file.Files
import java.nio.file.Path


object FilesManagment {
    fun getFilesRecursively(path: Path): List<Path> {
        var allFiles: List<Path> = listOf()
        Files.newDirectoryStream(path).use { stream ->
            for (entry in stream) {
                if (Files.isDirectory(entry)) {
                    allFiles += getFilesRecursively(entry)
                } else {
                    allFiles += (entry)
                }
            }
        }
        return allFiles
    }
}