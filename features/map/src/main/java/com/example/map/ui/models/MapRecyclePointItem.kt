package com.example.map.ui.models

import com.example.models.RecyclePointDTO

data class MapRecyclePointItem(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val locationHint: String?,
    val latitude: Double,
    val longitude: Double,
    val acceptedItems: List<String>,
    val workingHours: String,
    val phoneNumber: String?,
    val imageUrl: String?,
    val moderationStatus: Int
)

fun RecyclePointDTO.toMapRecyclePointItem() = MapRecyclePointItem(
    id= id,
    name = name,
    description = description,
    address = address,
    locationHint = locationHint,
    latitude = latitude,
    longitude = longitude,
    acceptedItems = categories,
    workingHours = workingHours,
    phoneNumber = phoneNumber,
    imageUrl = imageUrl,
    moderationStatus = moderationStatus
)

fun String.toStringCategory(): String {
    return when(this) {
        "0" -> "Бумага"
        "2" -> "Плсастик"
        "3" -> "Стекло"
        "4" -> "Металл"
        "5" -> "Тетра-пак"
        "6" -> "Одежда"
        "7" -> "Лампочки"
        "8" -> "Крышечки"
        "9" -> "Техника"
        "10" -> "Батарейки"
        "11" -> "Опасное"
        else -> "Другое"
    }
}
