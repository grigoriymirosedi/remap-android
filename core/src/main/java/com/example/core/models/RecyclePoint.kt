package com.example.core.models

data class RecyclePoint(
    val id: String,
    val name: String,
    val image: String?,
    val description: String,
    val contacts: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val working_hours: String,
    val categories: List<String>
)
