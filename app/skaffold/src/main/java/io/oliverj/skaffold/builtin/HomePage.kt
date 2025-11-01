package io.oliverj.skaffold.builtin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.oliverj.skaffold.EMPTY_LAMBDA
import io.oliverj.skaffold.Page
import io.oliverj.skaffold.data.PageData
import io.oliverj.skaffold.data.skafolded

class HomePage(onNext: () -> Unit = EMPTY_LAMBDA, onBack: () -> Unit = EMPTY_LAMBDA) : Page(onNext, onBack) {
    @Composable
    override fun Render() {
        val name: String by skafolded { "name" }

        Column {
            Text("Hello from $name")
        }
    }
}