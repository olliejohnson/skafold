package io.oliverj.skafold.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.oliverj.skafold.data.skafolded

@Composable
fun SKInput(
    fieldName: String,
    inputPattern: String,
    modifier: Modifier = Modifier
){
    var field: String by skafolded { fieldName }
    val fieldHeight = LocalFieldHeight.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(fieldHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = "",
            onValueChange = { field = it },
        )
    }
}