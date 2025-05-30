package com.example.events.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.TitleText
import com.example.core.uikit.RemapAppTheme
import com.example.events.components.Day
import com.example.events.components.MonthHeader
import com.example.events.models.RecycleEvent
import com.example.events.models.State
import com.example.ui.ProgressBar
import com.example.ui.RecycleEventItemCard
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.YearMonth

@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    uiState: State
) {
    when(uiState) {
        is State.Loading -> ProgressBar()
        is State.Error -> ProgressBar()
        is State.Success -> {
            EventScreenContent(
                modifier = modifier,
                events = uiState.data.recycleEvents
            )
        }
    }
}

@Composable
private fun EventScreenContent(
    modifier: Modifier = Modifier,
    events: List<RecycleEvent>
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY)

    Column(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        HorizontalCalendar(
            modifier = Modifier.background(RemapAppTheme.colorScheme.brandBackground),
            state = state,
            monthHeader = { MonthHeader(it, daysOfWeek) },
            dayContent = { Day(it) }
        )
        TitleText(text = "Предстоящие мероприятия")
        events.forEach { event ->
            RecycleEventItemCard(
                imageUrl = event.imageUrl ?: "",
                eventTitle = event.title,
                eventDescription = event.description,
                eventDateDay = event.date.split('-')[2],
                eventDateMonth = (event.date.split('-')[1]).toMonthType(),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun EventScreenContentPreview() {
    val events = listOf(
        RecycleEvent(
            id = "1",
            title = "Экологическая уборка в парке",
            description = "Присоединяйтесь к нам для уборки мусора в центральном парке города. Перчатки и мешки будут предоставлены.",
            time = "10:00",
            date = "15 Февраля",
            location = "Центральный парк, ул. Зеленая, 15",
            imageUrl = "https://example.com/images/park_cleanup.jpg"
        ),
        RecycleEvent(
            id = "2",
            title = "Мастер-класс по переработке пластика",
            description = "Научитесь создавать полезные вещи из переработанного пластика. Все материалы включены.",
            time = "14:30",
            date = "20 Марта",
            location = "Экоцентр 'Зеленая планета', ул. Экологическая, 42",
            imageUrl = "https://example.com/images/plastic_workshop.jpg"
        ),
        RecycleEvent(
            id = "3",
            title = "Сбор батареек и электроники",
            description = "Приносите старые батарейки и электронику для правильной утилизации. Получите скидку в эко-магазине за участие.",
            time = "11:00-18:00",
            date = "25 Апреля",
            location = "Торговый центр 'ЭкоМолл', 3 этаж",
            imageUrl = null
        ),
        RecycleEvent(
            id = "4",
            title = "Экскурсия на завод по переработке",
            description = "Уникальная возможность увидеть, как перерабатывают мусор на современном заводе. Для всех возрастов.",
            time = "09:00",
            date = "30 Августа",
            location = "Завод 'ЭкоТех', промзона 'Северная'",
            imageUrl = "https://example.com/images/recycling_plant.jpg"
        ),
        RecycleEvent(
            id = "5",
            title = "Обмен вещами 'Second Life'",
            description = "Принесите ненужные вещи в хорошем состоянии и обменяйте их на что-то полезное для вас.",
            time = "12:00-16:00",
            date = "5 Июня",
            location = "Коворкинг 'Зеленый офис', ул. Круговоротная, 7",
            imageUrl = "https://example.com/images/swap_event.jpg"
        )
    )
    EventScreenContent(
        events = events
    )
}

fun String.toMonthType(): String {
    return when(this) {
        "1" -> "Января"
        "2" -> "Февраля"
        "3" -> "Марта"
        "4" -> "Апреля"
        "5" -> "Мая"
        "6" -> "Июня"
        "7" -> "Июля"
        "8" -> "Августа"
        "9" -> "Сентября"
        "10" -> "Октября"
        "11" -> "Ноября"
        "12" -> "Декабря"
        else -> "Января"
    }
}