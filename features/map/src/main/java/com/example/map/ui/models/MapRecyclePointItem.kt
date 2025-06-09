package com.example.map.ui.models

import com.example.models.RecyclePointDTO
import java.util.UUID

data class MapRecyclePointItem(
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
    val moderationStatus: Int,
    val isDummy: Boolean
)

fun RecyclePointDTO.toMapRecyclePointItem() = MapRecyclePointItem(
    name = name,
    description = description,
    address = address,
    locationHint = locationHint,
    latitude = latitude,
    longitude = longitude,
    acceptedItems = categories.map { it.toStringCategory() },
    workingHours = workingHours,
    phoneNumber = phoneNumber,
    imageUrl = imageUrl,
    moderationStatus = moderationStatus,
    isDummy = false
)

fun MapRecyclePointItem.toRecyclePointDTO() = RecyclePointDTO(
    id = UUID.randomUUID().toString(),
    name = name,
    description = description,
    address = address,
    locationHint = locationHint,
    latitude = latitude,
    longitude = longitude,
    categories = acceptedItems,
    workingHours = workingHours,
    phoneNumber = phoneNumber,
    imageUrl = "",
    moderationStatus = 0,
)

fun String.toStringCategory(): String {
    return when(this) {
        "0" -> "Бумага"
        "1" -> "Пластик"
        "2" -> "Стекло"
        "3" -> "Металл"
        "4" -> "Тетра-пак"
        "5" -> "Одежда"
        "6" -> "Лампочки"
        "7" -> "Крышечки"
        "8" -> "Техника"
        "9" -> "Батарейки"
        "10" -> "Шины"
        "11" -> "Опасное"
        else -> "Другое"
    }
}
