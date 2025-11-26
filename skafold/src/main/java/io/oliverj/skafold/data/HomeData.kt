package io.oliverj.skafold.data

import io.oliverj.skafold.processor.SerializableData
import kotlinx.serialization.Serializable

/**
 * Builtin home page data. This is the data for the builtin home page.
 *
 * @see io.oliverj.skafold.builtin.HomePage
 * @author Oliver Johnson
 */
@Serializable
@SerializableData
class HomeData : PageData("home")
