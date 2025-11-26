package io.oliverj.skaffold.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SKLabel(
    label: String,
    modifier: Modifier = Modifier
) {
    Text(label, modifier=modifier)
}