package io.oliverj.skafold.builtin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.oliverj.skafold.EMPTY_LAMBDA
import io.oliverj.skafold.Page
import io.oliverj.skafold.Skafold
import io.oliverj.skafold.component.SKForm
import io.oliverj.skafold.component.SKFormTitle
import io.oliverj.skafold.component.SKInput
import io.oliverj.skafold.component.SKLabeledComposable
import io.oliverj.skafold.data.GameData
import io.oliverj.skafold.data.skafolded
import kotlin.collections.set

/**
 * This is the builtin game page. This is used to show information like
 * scouter name, team number, match number, competition id, and all of the other
 * pre-match information.
 *
 * @author Oliver Johnson
 */
class GamePage(onNext: () -> Unit = EMPTY_LAMBDA, onBack: () -> Unit = EMPTY_LAMBDA) : Page(onNext, onBack) {

    /**
     * This is the function that contains all of the ui code for this page.
     *
     * @author Oliver Johnson
     */
    @Composable
    override fun Render() {
        val name: String by skafolded { "name" }
        SKForm(
            Modifier.padding(10.dp),
            labelWidth = 150.dp,
            componentWidth = 200.dp,
            fieldHeight = 40.dp,
            rowSpacing = 8.dp
        ) {
            SKFormTitle("Start Match")
            HorizontalDivider()
            SKLabeledComposable("Team Number") {
                SKInput("teamNumber", inputPattern = "[0-9]+")
            }
            SKLabeledComposable("Scout Name") {
                SKInput("scoutName", inputPattern = "*")
            }
            SKLabeledComposable("Match Number") {
                SKInput("matchNumber", inputPattern = "[0-9]+")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewGame() {
    Skafold.currentPage = "game"
    Skafold.pageData["game"] = GameData()

    GamePage().Core({})

}