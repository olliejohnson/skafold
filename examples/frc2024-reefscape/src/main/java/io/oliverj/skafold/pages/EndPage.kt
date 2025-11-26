package io.oliverj.skafold.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.oliverj.skafold.EMPTY_LAMBDA
import io.oliverj.skafold.Page

class EndPage(onNext: () -> Unit = EMPTY_LAMBDA, onBack: () -> Unit = EMPTY_LAMBDA) : Page(onNext, onBack) {

    @Composable
    override fun Render() {
        Text("End Page")
    }
}