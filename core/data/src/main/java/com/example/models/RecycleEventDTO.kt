package com.example.models

import com.example.core.serializers.LocalDateSerializer
import com.example.core.serializers.LocalTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class RecycleEventDTO(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("image_url")
    val imageUrl: String?,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,
    @Serializable(with = LocalTimeSerializer::class)
    val time: LocalTime,
    val location: String,
)


