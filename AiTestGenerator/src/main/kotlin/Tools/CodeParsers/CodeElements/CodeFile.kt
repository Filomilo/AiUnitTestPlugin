package Tools.CodeParsers.CodeElements

import com.jetbrains.rd.util.Queue
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Stack

abstract class CodeFile(
    open val dependecies: Set<String> = setOf(),
    open val codes: MutableList<Code> = mutableListOf(),
    open var file: File? = null,
) {
    abstract fun getMethods(): Collection<Code>
    fun getMethodsWithParents(): Collection<Code> {
        var codes: Collection<Code> = getMethods().map {
            var curr: Code = it
            var stack: Stack<Code> = Stack()
            while (curr.parent != null) {
                stack.push(curr)
                curr = curr.parent!!
            }
            var node: Code = curr.copy()
            var walk: Code = node
            stack.forEach { x ->
                walk.children = listOf<Code>(x)
                walk = x
            }

            node
        }.toList()
        return codes
    }

    abstract fun getContent(): String

}
