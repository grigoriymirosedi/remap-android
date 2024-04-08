package com.example.remap.ui.screens.calendar.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.core.CalendarDay

@Composable
fun Day(day: CalendarDay, eventNum: Int = 0, onClick: (CalendarDay) -> Unit) {
    Column(
        modifier = Modifier.aspectRatio(1f).clickable { onClick(day) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
        )
        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (eventNum == 1) {
                Dot()
            }
            else if (eventNum == 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Dot()
                    Dot()
                }
            }
            else if (eventNum == 3) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Dot()
                    Dot()
                    Dot()
                }
            }
        }

    }
}

@Composable
fun Dot() {
    androidx.compose.foundation.Canvas(
        modifier = Modifier.size(4.dp)
    ) {
        drawCircle(color = Color(0xff5dBB63))
    }
}