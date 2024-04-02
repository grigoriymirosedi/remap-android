package com.example.remap.domain.models

import com.example.remap.data.remote.dto.CalendarEventDTO
import java.time.LocalDate
import java.time.LocalTime

data class CalendarEvent(
    val id: String,
    val title: String,
    val description: String,
    val photo_url: String,
    val event_date: LocalDate,
    val event_start_time: LocalTime,
    val event_location: String,
    val event_color: String
)

fun CalendarEvent.toCalendarEventDTO() = CalendarEventDTO(
    id = id,
    title = title,
    description = description,
    photo_url = photo_url,
    event_date = event_date,
    event_start_time = event_start_time,
    event_location = event_location,
    event_color = event_color
)
