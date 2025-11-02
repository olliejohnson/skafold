package io.oliverj.skaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

val EMPTY_LAMBDA: () -> Unit = {}

/**
 * A page is a navigable panel of compose components. You register pages in the
 * skafold lambda. All pages have some form of data attached to them.
 *
 * @see Skafold
 * @see io.oliverj.skaffold.data.PageData
 * @author Oliver Johnson
 */
abstract class Page(val next: () -> Unit = EMPTY_LAMBDA, var back: () -> Unit = EMPTY_LAMBDA) {
    var id: String = ""

    @Composable
    abstract fun Render()

    @Composable
    fun Core(saveData: () -> Unit, modifier: Modifier = Modifier) {
        Box(modifier.fillMaxSize()) {
            Column {
                Render()
            }
            Row(Modifier.align(Alignment.BottomCenter), horizontalArrangement = Arrangement.SpaceBetween) {
                if (back != EMPTY_LAMBDA) {
                    Button({ back(); saveData() }) {
                        Text("Back")
                    }
                }

                if (next != EMPTY_LAMBDA) {
                    Button({ next(); saveData() }) {
                        Text("Next")
                    }
                }
            }
        }
    }
}