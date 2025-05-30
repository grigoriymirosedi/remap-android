package com.example.home.models

import com.example.home.ui.toProfileInfo
import com.example.home.ui.toRecycleEvent
import com.example.models.ProfileInfoDTO
import com.example.models.RecycleEventDTO
import com.example.utils.RequestResult

data class RecycleEvent(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val time: String = "",
    val location: String = "",
    val imageUrl: String = ""
)

fun RequestResult<List<RecycleEventDTO>>.toRecycleEvent(): List<RecycleEvent> {
    return if(this is RequestResult.Success) {
        data?.map { it.toRecycleEvent() } ?: emptyList()
    } else emptyList()
}
