package io.oliverj.skafold.data

import io.oliverj.skafold.processor.SerializableData
import kotlinx.serialization.Serializable

@Serializable
@SerializableData
data class AutonData(var points: Int = 0) : PageData("auton") {
}