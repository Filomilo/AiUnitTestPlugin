package Tools.CodeParsers.CodeElements

import Tools.CodeParsers.ParsingException
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code


class PythonFunctionBuilder() {

    constructor(pythonClassBuilder: PythonClassBuilder) : this() {
        this.pythonClassBuilder = pythonClassBuilder
    }

    constructor(PythonCodeFileBuilder: PythonCodeFileBuilder) : this() {
        this.PythonCodeFileBuilder = PythonCodeFileBuilder
    }

    private var pythonClassBuilder: PythonClassBuilder? = null;
    private var PythonCodeFileBuilder: PythonCodeFileBuilder? = null;


    private lateinit var decleration: String
    private lateinit var body: String

    fun setDeclaration(declaration: String): PythonFunctionBuilder {
        this.decleration = declaration
        return this;
    }

    fun setBody(body: String): PythonFunctionBuilder {
        this.body = body
        return this;
    }

    fun finish(): Any {
        var bodyCode: Code = Code(body)
        var delcartionCode: Code = Code(decleration, listOf(bodyCode))
        if (pythonClassBuilder != null) {
            this.pythonClassBuilder!!.Function += (delcartionCode); return pythonClassBuilder!!
        } else this.PythonCodeFileBuilder!!.functions += (delcartionCode)

        return this.PythonCodeFileBuilder!!
    }
}


class PythonClassBuilder(pythonCodeFileBuilder: PythonCodeFileBuilder) {

    private lateinit var name: String
    private var variables: Set<String> = setOf<String>()
    var Function: MutableSet<Code> = mutableSetOf<Code>()

    fun finishClass(): PythonCodeFileBuilder {
        val classCode: Code = Code(name)
        var codes: List<Code> = (variables.map { x -> Code(x) }.toSet() + Function).toList()
        codes.forEach { x -> x.parent = classCode }
        classCode.children = codes
        this.pythonCodeFileBuilder.classes += classCode
        return this.pythonCodeFileBuilder
    }

    private var pythonCodeFileBuilder: PythonCodeFileBuilder;

    init {
        this.pythonCodeFileBuilder = pythonCodeFileBuilder;
    }

    fun setNameDeclaration(name: String): PythonClassBuilder {
        this.name = name;
        return this
    }


    fun addVariable(delcartion: String): PythonClassBuilder {
        this.variables += delcartion
        return this;
    }

    fun addFunction(): PythonFunctionBuilder {

        return PythonFunctionBuilder(this)
    }


}


class PythonCodeFileBuilder {
    private var imports: Set<String> = setOf()
    var classes: MutableList<Code> = mutableListOf()
    var functions: MutableList<Code> = mutableListOf()


    fun addImport(importName: String): PythonCodeFileBuilder {
        this.imports += (importName)
        return this;
    }

    fun addClass(): PythonClassBuilder {
        return PythonClassBuilder(this)
    }

    fun addFunction(): PythonFunctionBuilder {
        return PythonFunctionBuilder(this)
    }

    fun build(): PythonCodeFile {
        if (classes.isEmpty() && functions.isEmpty()) {
            throw ParsingException("no Python code found")
        }
        val codes: MutableList<Code> = mutableListOf()
        codes.addAll(functions)
        codes.addAll(classes)
        return PythonCodeFile(
            dependecies = this.imports,
            codes = codes
        )
    }

    fun addLine(line: Code) {
        functions.add(line)
    }
}


