package com.example.events.models

import com.example.models.RecycleEventDTO

data class RecycleEvent(
    val id: String,
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val location: String,
    val imageUrl: String?
)

fun RecycleEventDTO.toRecycleEvent() = RecycleEvent(
    id = id,
    title = title,
    description = description,
    time = time.toString(),
    date = date.toString(),
    location = location,
    imageUrl = imageUrl
)
