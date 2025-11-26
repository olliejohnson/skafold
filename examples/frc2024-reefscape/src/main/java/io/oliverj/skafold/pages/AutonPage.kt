package io.oliverj.skafold.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.oliverj.skafold.EMPTY_LAMBDA
import io.oliverj.skafold.Page
import io.oliverj.skafold.data.skafolded

class AutonPage(onNext: () -> Unit = EMPTY_LAMBDA, onBack: () -> Unit = EMPTY_LAMBDA) : Page(onNext, onBack) {
    @Composable
    override fun Render() {
        var name: String by skafolded { "name" }
        var points: Int by skafolded { "points" }

        Column {
            Text("$name page")
            Text("Points: $points")
            Button({points += 1}) {
                Text("Inc")
            }
        }
    }
}