package com.example.home.models

import com.example.home.ui.toRecyclePoint
import com.example.models.RecyclePointDTO
import com.example.utils.RequestResult

data class RecyclePoint(
    val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val distance: Double = 0.0,
    val acceptedItems: List<String>,
    val workingHours: String,
    val phoneNumber: String?,
    val imageUrl: String?,
)

fun RequestResult<List<RecyclePointDTO>>.toRecyclePoints(): List<RecyclePoint> {
    return if(this is RequestResult.Success) {
        data?.map { it.toRecyclePoint() } ?: emptyList()
    } else emptyList()
}