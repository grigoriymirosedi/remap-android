package com.example.remap.ui.screens.calendar

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remap.ui.screens.calendar.utils.Day
import com.example.remap.ui.screens.calendar.utils.DaysOfWeekTitle
import com.example.remap.ui.screens.calendar.utils.MonthTitle
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.YearMonth

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: CalendarViewModel = hiltViewModel(),
) {

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(3) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(3) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val eventList = viewModel.events.collectAsState()

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    Log.d("123123", eventList.value.toString())

    HorizontalCalendar(
        state = state,
        dayContent = { Day(it, eventNum = 3) },
        monthHeader = { month ->
            val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
            MonthTitle(month = month.yearMonth.month)
            DaysOfWeekTitle(daysOfWeek = daysOfWeek)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}