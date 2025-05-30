package com.example.events.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun MonthHeader(
    month: CalendarMonth,
    daysOfWeek: List<DayOfWeek>
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = month.yearMonth.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()),
                style = RemapAppTheme.typography.subheading1.copy(fontWeight = FontWeight.SemiBold),
                color = RemapAppTheme.colorScheme.brandTextDefault
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.SemiBold),
                    text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                )
            }
        }
    }
}