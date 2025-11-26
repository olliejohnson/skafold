package io.oliverj.skaffold.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SKYesNoChoice (
    fieldName: String,
    modifier: Modifier = Modifier
) {
    SKChoice(
        fieldName,
        listOf("No","Yes"),
        {x -> (x=="Yes")},
        modifier )
}