package com.example.remap.ui.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 24.sp,
    color: Color = Color.Unspecified,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(text = text, modifier = modifier, color = color, fontSize = fontSize, textAlign = textAlign, fontWeight = fontWeight)
}

@Preview(showBackground = true)
@Composable
fun TitleTextPreview(text: String = "Тестовый сбор у мусорки мехмата") {
    TitleText(text = text)
}