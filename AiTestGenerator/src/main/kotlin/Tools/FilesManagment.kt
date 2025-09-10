package org.filomilo.AiTestGenerator.Tools

import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.isDirectory


object FilesManagment {
    fun getFilesRecursively(path: Path): List<Path> {

        return Files.walk(path).skip(1).toList()

    }


    fun funRemoveDirectory(path: Path) {
        FileUtils.deleteDirectory(File(path.toAbsolutePath().toUri()));
    }

    fun copyDirectory(src: Path, dest: Path) {
        FileUtils.copyDirectory(src.toFile(), dest.toFile())
    }

    fun findFilesByExtensions(directory: Path, extensions: List<String>): List<Path> {
        return Files.walk(directory).filter(Files::isRegularFile).map { x -> x.toFile() }
            .filter { x -> x.extension in extensions }.map { x -> x.toPath() }.toList()
    }

    fun deleteDirecotry(projectPath: Path) {
        deleteContentOfFolder(projectPath)
        Files.delete(projectPath)
    }

    fun deleteContentOfFolder(pathForTestFolder: Path) {
        Files.walk(pathForTestFolder).skip(1).forEach { x -> x.toFile().deleteRecursively() }
    }

    fun deleteFilse(reportFiles: Collection<Path>) {
        reportFiles.forEach { x ->
            if (x.exists()) {
                if (x.isDirectory()) {
                    FilesManagment.deleteDirecotry(x)
                } else
                    Files.delete(x)
            }
        }
    }

}