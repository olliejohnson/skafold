package io.oliverj.skaffold.data

import io.oliver.processor.SerializableData
import kotlinx.serialization.Serializable

@Serializable
@SerializableData
class HomeData : PageData("home")
