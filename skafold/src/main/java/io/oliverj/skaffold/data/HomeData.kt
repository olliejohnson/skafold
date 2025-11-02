package io.oliverj.skaffold.data

import io.oliver.processor.SerializableData
import kotlinx.serialization.Serializable

/**
 * Builtin home page data. This is the data for the builtin home page.
 *
 * @see io.oliverj.skaffold.builtin.HomePage
 * @author Oliver Johnson
 */
@Serializable
@SerializableData
class HomeData : PageData("home")
