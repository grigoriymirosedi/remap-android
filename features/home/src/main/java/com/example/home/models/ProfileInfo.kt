package com.example.home.models

import com.example.home.ui.toProfileInfo
import com.example.models.ProfileInfoDTO
import com.example.utils.RequestResult

data class ProfileInfo(
    val username: String = "",
    val email: String = "",
    val points: Int = 0,
    val tip: String = "",
    val requests: List<RequestItem> = emptyList(),
    val collectedItems: List<CollectedItem> = emptyList()
)

fun RequestResult<ProfileInfoDTO>.toProfileInfo(): ProfileInfo {
    return if(this is RequestResult.Success) {
        data?.toProfileInfo(
        ) ?: ProfileInfo()
    } else ProfileInfo()
}