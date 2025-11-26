package io.oliverj.skafold.builtin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.oliverj.skafold.EMPTY_LAMBDA
import io.oliverj.skafold.Page
import io.oliverj.skafold.data.skafolded

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
            Text("DERP!!! Hello from $name")
        }
    }
}