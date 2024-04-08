package com.example.remap.ui.screens.calendar.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remap.core.util.Constants
import com.example.remap.domain.models.CalendarEvent
import com.example.remap.ui.theme.md_theme_light_background
import com.example.remap.ui.utils.TextWithIcon
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsBottomSheet(modifier: Modifier = Modifier, eventsList: List<CalendarEvent>, sheetState: SheetState, onDismiss: () -> Unit) {
    ModalBottomSheet(containerColor = md_theme_light_background,sheetState = sheetState, onDismissRequest = onDismiss) {
        LazyColumn(modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)) {
            items(eventsList) {event ->
                Spacer(modifier = Modifier.size(8.dp))
                EventBottomSheetContent(event = event)
            }
        }
    }
}

@Preview()
@Composable
fun EventBottomSheetContent(
    event: CalendarEvent = CalendarEvent(
        "123",
        "Ежегодное собрание около мехматовской мусорки",
        "test desc",
        "123",
        LocalDate.now(),
        LocalTime.now(),
        "st. Milchakova 8a",
        "#021341"
    ),
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            text = event.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
//        Image(modifier = Modifier
//            .height(200.dp)
//            .fillMaxWidth(),
//            painter = painterResource(id = R.drawable.recycle_point_sample_image),
//            contentScale = ContentScale.Crop,
//            contentDescription = null)
        Column(
            modifier = Modifier.align(Alignment.End).padding(end = 16.dp, top = 8.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            TextWithIcon(
                text = event.event_location,
                icon = Icons.Outlined.LocationOn,
                contentColor = Color.Gray,
            )
            TextWithIcon(
                text = event.event_date.format(Constants.formatterRU) + " " + event.event_start_time.format(DateTimeFormatter.ofPattern("HH:mm")),
                icon = Icons.Outlined.CalendarMonth,
                contentColor = Color.Gray
            )
        }
    }
}