package com.example.auth

import com.example.models.UserLoginParams
import com.example.models.UserRegisterParams
import com.example.models.UserTokenDTO
import com.example.utils.RequestResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(userLoginParams: UserLoginParams): Flow<RequestResult<UserTokenDTO>>

    fun register(userRegisterParams: UserRegisterParams): Flow<RequestResult<UserTokenDTO>>
}