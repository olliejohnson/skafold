package io.oliverj.skaffold.builtin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.oliverj.skaffold.EMPTY_LAMBDA
import io.oliverj.skaffold.Page
import io.oliverj.skaffold.data.skafolded

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

        Column {
            Text("Game page: $name")
        }
    }
}