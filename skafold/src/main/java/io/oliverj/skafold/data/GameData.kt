package io.oliverj.skafold.data

import io.oliverj.skafold.processor.SerializableData
import kotlinx.serialization.Serializable

/**
 * Builtin game page data. This is a class that contains the data for the game page.
 *
 * @see io.oliverj.skafold.builtin.GamePage
 * @author Oliver Johnson
 */
@Serializable
@SerializableData
class GameData : PageData("game")