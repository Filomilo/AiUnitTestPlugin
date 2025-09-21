package org.filomilo.AiTestGenerator.Tools

import com.intellij.testFramework.statement
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.serializer
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.time.Instant
import java.util.Collections.emptyList
import java.util.Stack
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.name
import kotlin.io.path.relativeTo


@Serializable()
data class PathObject(
    @Contextual
    val name: String,
    val children: MutableList<PathObject>,
    val content: String?
)


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
                    deleteDirecotry(x)
                } else
                    Files.delete(x)
            }
        }
    }


    fun getFolderContent(path: Path, ignoredPaths: List<Path> = emptyList()): List<PathObject> {
        var content: MutableList<PathObject> = emptyList<PathObject>().toMutableList()
        val pathStack: Stack<PathObject> = Stack()
        Files.walkFileTree(path, object : SimpleFileVisitor<Path?>() {
            private var depth = 0

            override fun preVisitDirectory(dir: Path?, attrs: BasicFileAttributes): FileVisitResult {
                if (ignoredPaths.contains(dir!!.relativeTo(path))) {
                    return FileVisitResult.SKIP_SUBTREE
                }
                val pathObject: PathObject = PathObject(
                    dir!!.name,
                    children = mutableListOf(),
                    content = null
                )
                if (pathStack.isNotEmpty()) {
                    pathStack.peek().children.add(pathObject)
                }
                pathStack.push(
                    pathObject
                )

                return FileVisitResult.CONTINUE
            }

            override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                if (ignoredPaths.contains(file!!.relativeTo(path))) {
                    return FileVisitResult.SKIP_SUBTREE
                }
                pathStack.peek().children.add(
                    PathObject(
                        name = file!!.name,
                        children = emptyList(),
                        content = file!!.toFile().readText()
                    )
                )
                return FileVisitResult.CONTINUE
            }

            override fun postVisitDirectory(dir: Path?, exc: IOException?): FileVisitResult {
                val pathObject: PathObject = pathStack.pop()
                if (pathStack.isEmpty()) {
                    content = pathObject.children
                }
                return FileVisitResult.CONTINUE
            }


        })

        return content
    }

}