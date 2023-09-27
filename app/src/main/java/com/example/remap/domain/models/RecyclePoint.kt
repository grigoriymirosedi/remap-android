package com.example.remap.domain.models

data class RecyclePoint(
    val id: Int,
    val name: String,
    val image: String?,
    val description: String,
    val contacts: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val working_hours: String,
)
