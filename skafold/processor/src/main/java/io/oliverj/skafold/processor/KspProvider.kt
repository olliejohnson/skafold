package io.oliverj.skafold.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

/**
 * Used to register [PageDataProcessor] as a KspProvider.
 *
 * @see PageDataProcessor
 * @author Oliver Johnson
 */
class KspProvider : SymbolProcessorProvider {
    /**
     * The method called to create the symbol processor.
     *
     * @author Oliver Johnson
     */
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return PageDataProcessor(environment.codeGenerator)
    }
}