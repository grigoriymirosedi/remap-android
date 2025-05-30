package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class CollectedItemDTO(
    val batteriesUnit: Int,
    val plasticUnit: Int,
    val paperUnit: Int
)
