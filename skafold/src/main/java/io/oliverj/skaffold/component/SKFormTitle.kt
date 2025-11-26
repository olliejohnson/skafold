package io.oliverj.skaffold.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SKFormTitle(title: String) {
    Text(title, style = MaterialTheme.typography.titleLarge)
}