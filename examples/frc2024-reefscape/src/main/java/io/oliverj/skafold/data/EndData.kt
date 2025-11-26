package io.oliverj.skafold.data

import io.oliverj.skafold.processor.SerializableData
import kotlinx.serialization.Serializable

@Serializable
@SerializableData
class EndData : PageData("end")