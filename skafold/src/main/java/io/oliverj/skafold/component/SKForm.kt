package io.oliverj.skafold.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalLabelWidth = compositionLocalOf { 150.dp }
val LocalComponentWidth = compositionLocalOf { 200.dp }

val LocalFieldHeight = compositionLocalOf { 40.dp }

@Composable
fun SKForm(
    modifier: Modifier = Modifier,
    labelWidth: Dp = 150.dp,
    componentWidth: Dp = 200.dp,
    fieldHeight: Dp = 40.dp,
    rowSpacing: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalLabelWidth provides labelWidth,
        LocalComponentWidth provides componentWidth,
        LocalFieldHeight provides fieldHeight) {
        Column(
            modifier,
            verticalArrangement = Arrangement.spacedBy(rowSpacing)
        ) {
            content()
        }
    }
}