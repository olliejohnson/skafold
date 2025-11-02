package io.oliverj.skaffold.data

import io.oliver.processor.SerializableData
import kotlinx.serialization.Serializable

@Serializable
@SerializableData
open class PageData(val name: String) {
}