package com.example.remap.ui.screens.calendar.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun MonthTitle(month: Month){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}
