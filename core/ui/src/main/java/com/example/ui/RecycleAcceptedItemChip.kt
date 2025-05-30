package com.example.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun RecycleAcceptedItemChip(
    modifier: Modifier = Modifier,
    item: String,
    itemTextStyle: TextStyle = RemapAppTheme.typography.metadata1,
    containerColor: Color = RemapAppTheme.colorScheme.brandDefault,
    borderColor: Color = RemapAppTheme.colorScheme.brandTextSecondary,
    contentColor: Color = RemapAppTheme.colorScheme.brandTextDefault,
    shape: Shape = RemapAppTheme.shape.medium
) {
    AssistChip(
        modifier = modifier,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor,
        ),
        border = AssistChipDefaults.assistChipBorder(
            enabled = true,
            borderWidth = 1.dp,
            borderColor = borderColor
        ),
        shape = shape,
        onClick = {},
        label = { Text(text = item, style = itemTextStyle.copy(fontWeight = FontWeight.Bold), color = contentColor)}
    )
}

@Preview(showBackground = true)
@Composable
private fun RecycleAcceptedItemChipPreview(
    item: String = "Пластик"
) {
    RemapTheme {
        RecycleAcceptedItemChip(
            item = item
        )
    }
}