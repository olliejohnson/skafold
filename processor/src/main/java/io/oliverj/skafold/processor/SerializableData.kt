package io.oliverj.skafold.processor

/**
 * An annotation used to signify a data class that is exported.
 * This must be applied to all classes that will be exported to json.
 *
 * @author Oliver Johnson
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class SerializableData()
