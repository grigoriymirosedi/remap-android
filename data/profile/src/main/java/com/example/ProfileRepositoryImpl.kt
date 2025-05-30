package com.example

import com.example.core.api.RemapAPI
import com.example.models.ProfileInfoDTO
import com.example.utils.RequestResult
import com.example.utils.toRequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val api: RemapAPI): ProfileRepository {
    override fun getUserInfo(): Flow<RequestResult<ProfileInfoDTO>> = flow {
        emit(api.getUserInfo())
    }.map { it.toRequestResult() }
}