package com.example

import com.example.models.ProfileInfoDTO
import com.example.utils.RequestResult
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUserInfo(): Flow<RequestResult<ProfileInfoDTO>>
}