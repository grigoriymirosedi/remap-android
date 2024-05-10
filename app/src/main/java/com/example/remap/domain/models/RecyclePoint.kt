package com.example.remap.domain.models

import com.example.remap.data.remote.dto.RecyclePointDTO

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

fun RecyclePoint.toRecyclePointDTO() = RecyclePointDTO(
    id = id,
    name = name,
    image = image,
    description = description,
    contacts = contacts,
    latitude = latitude,
    longitude = longitude,
    address = address,
    working_hours = working_hours,
    categories = categories
)
