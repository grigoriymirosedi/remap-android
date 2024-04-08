package com.example.remap.ui.screens.calendar

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remap.domain.models.CalendarEvent
import com.example.remap.ui.screens.calendar.components.Day
import com.example.remap.ui.screens.calendar.components.DaysOfWeekTitle
import com.example.remap.ui.screens.calendar.components.EventsBottomSheet
import com.example.remap.ui.screens.calendar.components.MonthTitle
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: CalendarViewModel = hiltViewModel(),
) {

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(3) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(3) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val calendarEventList = viewModel.events.collectAsState().value.groupBy { it.event_date }

    var eventContentValue by remember { mutableStateOf<List<CalendarEvent>>(emptyList()) }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    HorizontalCalendar(
        state = state,
        dayContent = { day: CalendarDay -> Day(day, eventNum = calendarEventList[day.date]?.size ?: 0) {
            eventContentValue = calendarEventList[day.date] ?: emptyList()
            showBottomSheet = true
            Log.d("1234", day.date.toString())
        } },
        monthHeader = { month ->
            val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
            MonthTitle(month = month.yearMonth.month)
            DaysOfWeekTitle(daysOfWeek = daysOfWeek)
        }
    )

    if(showBottomSheet) {
        EventsBottomSheet(eventsList = eventContentValue, sheetState = sheetState, onDismiss = {
            showBottomSheet = false
        })
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}