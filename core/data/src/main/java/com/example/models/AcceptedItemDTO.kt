package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class AcceptedItemDTO(
    val id: String,
    val name: String
)