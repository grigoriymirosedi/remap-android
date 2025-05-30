package com.example.models

import com.example.core.serializers.LocalTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalTime

@Serializable
data class RequestItemDTO(
    @SerialName("request_id")
    val requestId: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("request_number")
    val requestNumber: String,
    val title: String,
    val status: Int,
    @Serializable(with = LocalTimeSerializer::class)
    val createdAt: LocalTime
)
