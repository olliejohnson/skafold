package io.oliverj.skaffold.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.oliverj.skaffold.data.skafolded

@Composable
fun <T> SKChoice(
    fieldName: String,
    options: List<String>,
    convertType: (String) -> T,
    modifier: Modifier = Modifier
) {
    var field: T by skafolded { fieldName }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val fieldHeight = LocalFieldHeight.current

    SingleChoiceSegmentedButtonRow(
        modifier = modifier
            .fillMaxWidth()
            .height(fieldHeight)
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                modifier = Modifier.weight(1f),
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    selectedIndex = index
                    field = convertType(options[index])
                },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}