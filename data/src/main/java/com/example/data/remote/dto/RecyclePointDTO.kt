package com.example.data.remote.dto

import com.example.core.models.RecyclePoint


data class RecyclePointDTO(
    val id: String? = null,
    val name: String,
    val image: String?,
    val description: String,
    val contacts: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val working_hours: String,
    val categories: List<String>?
)

fun RecyclePointDTO.toRecyclePoint() = RecyclePoint(
    id = id ?: "",
    name = name,
    image = image,
    description = description,
    contacts = contacts,
    latitude = latitude,
    longitude = longitude,
    address = address,
    working_hours = working_hours,
    categories = categories ?: emptyList()
)