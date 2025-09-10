package org.filomilo.AiTestGenerator.Tools

import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.FileVisitOption
import java.nio.file.Files
import java.nio.file.Path


object FilesManagment {
    fun getFilesRecursively(path: Path): List<Path> {

       return Files.walk(path).skip(1)   .toList()

    }


    fun funRemoveDirectory(path:Path){
        FileUtils.deleteDirectory(File(path.toAbsolutePath().toUri()));
    }

    fun copyDirectory(src:Path, dest:Path) {
        FileUtils.copyDirectory(src.toFile(),dest.toFile())
    }

}