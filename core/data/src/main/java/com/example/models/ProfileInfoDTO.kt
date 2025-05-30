package com.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileInfoDTO(
    val username: String,
    val email: String,
    val points: Int,
    val tip: String,
    val requests: List<RequestItemDTO>,
    @SerialName("collectedItems")
    val collectedUnits: List<CollectedItemDTO>
)