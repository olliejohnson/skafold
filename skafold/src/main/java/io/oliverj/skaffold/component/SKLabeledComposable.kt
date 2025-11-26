package io.oliverj.skaffold.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SKLabeledComposable(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val labelWidth = LocalLabelWidth.current
    val componentWidth = LocalComponentWidth.current

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SKLabel(label,Modifier.width(labelWidth))

        Box(
            modifier = Modifier.width(componentWidth),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}