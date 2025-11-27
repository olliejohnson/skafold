package io.oliverj.skafold.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.oliverj.skafold.data.skafolded


@Composable
fun SKComment(
    fieldName: String,
    modifier: Modifier = Modifier
) {
    var field: String by skafolded { fieldName }
    val fieldHeight: Dp = 100.dp
    val maxLines: Int = 5

    OutlinedTextField(
        value = "",
        onValueChange = { field = it },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = fieldHeight),
        placeholder = { Text("Type your comments here…") },
        singleLine = false,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Default
        )
    )
}