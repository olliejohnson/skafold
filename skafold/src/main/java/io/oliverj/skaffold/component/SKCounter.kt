package io.oliverj.skaffold.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.oliverj.skaffold.data.skafolded
import kotlin.math.max
import kotlin.math.min

@Composable
fun SKCounter(
    fieldName: String,
    modifier: Modifier = Modifier,
    minValue: Int = 0,
    maxValue: Int = Int.MAX_VALUE
) {
    var field: Int by skafolded { fieldName }
    val fieldHeight = LocalFieldHeight.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(fieldHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            { field = max(minValue,field-1) },
            shape = RoundedCornerShape(25.dp, 0.dp, 0.dp, 25.dp),
        ) {
            Text("-")
        }
        Box(
            modifier = modifier
                .weight(1f)
                .height(fieldHeight)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text("$field", color = MaterialTheme.colorScheme.onPrimary)
        }
        Button(
            { field = min(maxValue,field+1) },
            shape = RoundedCornerShape(0.dp, 25.dp, 25.dp, 0.dp),
        ) {
            Text("+")
        }
    }
}