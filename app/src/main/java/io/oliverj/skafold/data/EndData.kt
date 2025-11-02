package io.oliverj.skafold.data

import io.oliver.processor.SerializableData
import io.oliverj.skaffold.data.PageData
import kotlinx.serialization.Serializable

@Serializable
@SerializableData
class EndData : PageData("end")