package io.oliverj.skafold.data

import io.oliver.processor.SerializableData
import io.oliverj.skaffold.data.PageData
import kotlinx.serialization.Serializable

@Serializable
@SerializableData
data class AutonData(var points: Int = 0) : PageData("auton") {
}