package io.oliverj.skafold.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate

/**
 * Used to generate serialization logic for user-generated data classes.
 *
 * @author Oliver Johnson
 */
class PageDataProcessor(private val codeGenerator: CodeGenerator) : SymbolProcessor {

    /**
     * Entrypoint into the ksp processor.
     *
     * @author Oliver Johnson
     */
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(SerializableData::class.qualifiedName!!)
        symbols.forEach { symbol ->
            generateCode(symbol as KSClassDeclaration, resolver)
        }
        return symbols.filterNot { it.validate() }.toList()
    }

    /**
     * Generates source code that is used to serialize data.
     *
     * @author Oliver Johnson
     */
    private fun generateCode(symbol: KSClassDeclaration, resolver: Resolver) {
        val packageName = symbol.packageName.asString()
        val className = symbol.simpleName.asString()
        val fqClassName = symbol.qualifiedName!!.asString()

        val generateCode = """
            package $packageName.generated
            
            import kotlinx.serialization.Serializable
            import kotlinx.serialization.json.Json
            import kotlinx.serialization.json.JsonElement
            import kotlinx.serialization.json.encodeToJsonElement
            import $fqClassName
            
            class ${className}Ext {
                companion object {
                    fun ${className}.save(): JsonElement {
                        val format = Json { encodeDefaults = true }
                        return format.encodeToJsonElement(this);
                    }
                }
            }
        """.trimIndent()

        val file = codeGenerator.createNewFile(
            dependencies = Dependencies(false),
            packageName = "${packageName}.generated",
            fileName = "${className}Ext"
        )

        file.write(generateCode.toByteArray())
    }
}