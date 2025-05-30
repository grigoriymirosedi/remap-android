package com.example.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun RecycleEventItemDate(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    textColor: Color = RemapAppTheme.colorScheme.brandDefault,
    day: String,
    month: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = RemapAppTheme.colorScheme.brandActive
        ),
        shape = shape
    ) {
        Column(
            modifier = modifier.padding(horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = day, style = RemapAppTheme.typography.heading1, color = textColor)
            Text(text = month, style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.Normal), color = textColor)
        }
    }
}

@Preview
@Composable
private fun RecycleItemDatePreview(
    modifier: Modifier = Modifier,
    day: String = "11",
    month: String = "Апреля"
) {
    RemapTheme {
        RecycleEventItemDate(
            modifier = modifier,
            day = day,
            month = month
        )
    }
}