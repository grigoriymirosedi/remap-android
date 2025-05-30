package com.example.core.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = RemapAppTheme.typography.heading2,
    color: Color = RemapAppTheme.colorScheme.brandTextDefault,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style,
    )
}

@Preview(showBackground = true)
@Composable
private fun TitleTextPreview(text: String = "Тестовый сбор у мусорки мехмата") {
    RemapTheme {
        TitleText(text = text)
    }
}