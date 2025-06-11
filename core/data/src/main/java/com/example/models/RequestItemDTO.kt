package com.example.models

import com.example.core.serializers.LocalDateSerializer
import com.example.core.serializers.LocalTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class RequestItemDTO(
    val requestNumber: String,
    val title: String,
    val category: String,
    val status: Int,
    val createdAt: String
)
