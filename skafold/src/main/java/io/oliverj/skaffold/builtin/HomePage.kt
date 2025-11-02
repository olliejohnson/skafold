package io.oliverj.skaffold.builtin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.oliverj.skaffold.EMPTY_LAMBDA
import io.oliverj.skaffold.Page
import io.oliverj.skaffold.data.skafolded

/**
 * This is the builtin home page. This is the first page you see when you start the app. It links to the
 * game page. This page can be replaced.
 *
 * @author Oliver Johnson
 */
class HomePage(onNext: () -> Unit = EMPTY_LAMBDA, onBack: () -> Unit = EMPTY_LAMBDA) : Page(onNext, onBack) {
    @Composable
    override fun Render() {
        val name: String by skafolded { "name" }

        Column {
            Text("Hello from $name")
        }
    }
}