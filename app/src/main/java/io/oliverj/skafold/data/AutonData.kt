package io.oliverj.skafold.data

import io.oliverj.skaffold.data.PageData
import kotlinx.serialization.Serializable

@Serializable
data class AutonData(override var name: String = "auton", var autonPoints: Int = 0) : PageData(name)
