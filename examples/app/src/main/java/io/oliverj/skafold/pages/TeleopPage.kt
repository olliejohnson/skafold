package io.oliverj.skafold.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.oliverj.skaffold.EMPTY_LAMBDA
import io.oliverj.skaffold.Page

class TeleopPage(onNext: () -> Unit = EMPTY_LAMBDA, onBack: () -> Unit = EMPTY_LAMBDA) : Page(onNext, onBack) {

    @Composable
    override fun Render() {
        Text("Teleop Page")
    }
}