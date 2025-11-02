package io.oliverj.skaffold.data

import io.oliver.processor.SerializableData
import kotlinx.serialization.Serializable

/**
 * This is the base class for all of the data objects for each page.
 * This contains the name property. This property is used to id the
 * pages and segment the json file.
 *
 * @author Oliver Johnson
 */
@Serializable
@SerializableData
open class PageData(val name: String) {
}