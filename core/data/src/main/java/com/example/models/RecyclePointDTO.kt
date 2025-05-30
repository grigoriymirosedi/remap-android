package com.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecyclePointDTO(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val locationHint: String,
    val latitude: Double,
    val longitude: Double,
    @SerialName("working_hours")
    val workingHours: String,
    val phoneNumber: String?,
    val imageUrl: String?,
    val moderationStatus: Int, // 0 - Pending, 1 - Approved
    val categories: List<String>,
)