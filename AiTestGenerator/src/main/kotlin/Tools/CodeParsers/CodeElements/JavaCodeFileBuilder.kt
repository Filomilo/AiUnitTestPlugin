package org.filomilo.AiTestGenerotorAnalisis.Tools.CodeParsers.CodeElements

import Tools.CodeParsers.CodeElements.JavaCodeFile
import Tools.CodeParsers.ParsingException
import com.intellij.codeInsight.hints.codeVision.CodeVision
import org.filomilo.AiTestGenerator.Tools.CodeParsers.CodeElements.Code

class JavaFunctionBuilder(javaClassBuilder: JavaClassBuilder) {
    private var javaClassBuilder: JavaClassBuilder;

    init {
        this.javaClassBuilder = javaClassBuilder;
    }

    private lateinit var decleration: String
    private lateinit var body: String
    private var code: Code = Code()

    fun setDeclaration(declaration: String): JavaFunctionBuilder {
        this.decleration = declaration
        return this;
    }

    fun setBody(body: String): JavaFunctionBuilder {
        this.body = body
        return this;
    }

    fun finish(): JavaClassBuilder {
        var bodyCode: Code = Code(body)
        var delcartionCode: Code = Code(decleration, listOf(bodyCode))
        this.javaClassBuilder.Function += (delcartionCode)
        return this.javaClassBuilder
    }
}


class JavaClassBuilder(javaCodeFileBuilder: JavaCodeFileBuilder) {

    private lateinit var name: String
    private var variables: Set<String> = setOf<String>()
    var Function: Set<Code> = setOf<Code>()

    fun finishClass(): JavaCodeFileBuilder {
        var code: Code = Code(name)
        Function.forEach { x -> x.parent = code }
        var codes: List<Code> =
            (variables.map { x -> Code(x, parent = code) }.toSet() + Function).toList()
        code.children = codes
        this.javaCodeFileBuilder.classes += code

        return this.javaCodeFileBuilder
    }

    private var javaCodeFileBuilder: JavaCodeFileBuilder;

    init {
        this.javaCodeFileBuilder = javaCodeFileBuilder;
    }

    fun setNameDeclaration(name: String): JavaClassBuilder {
        this.name = name;
        return this
    }


    fun addVariable(delcartion: String): JavaClassBuilder {
        this.variables += delcartion
        return this;
    }

    fun addFunction(): JavaFunctionBuilder {

        return JavaFunctionBuilder(this)
    }


}

class JavaCodeFileBuilder {
    private var packageName: String? = null
    private var imports: Set<String> = setOf()
    var classes: MutableList<Code> = mutableListOf()

    fun setPackageName(packageName: String): JavaCodeFileBuilder {
        this.packageName = packageName
        return this;
    }

    fun addImport(importName: String): JavaCodeFileBuilder {
        this.imports += (importName)
        return this;
    }

    fun addClass(): JavaClassBuilder {
        return JavaClassBuilder(this)
    }

    fun build(): JavaCodeFile {
        if (classes.isEmpty()) {
            throw ParsingException("no Java coe found")
        }
        return JavaCodeFile(
            packageDelaration = this.packageName,
            dependecies = this.imports,
            codes = classes
        )
    }
}

