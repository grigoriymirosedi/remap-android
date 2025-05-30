package com.example.auth

import com.example.core.api.RemapAPI
import com.example.models.UserLoginParams
import com.example.models.UserRegisterParams
import com.example.models.UserTokenDTO
import com.example.utils.RequestResult
import com.example.utils.toRequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: RemapAPI
): AuthRepository {

    override fun login(userLoginParams: UserLoginParams): Flow<RequestResult<UserTokenDTO>> {
        return flow{ emit(api.login(userLoginParams = userLoginParams)) }.map { it.toRequestResult() }
    }

    override fun register(userRegisterParams: UserRegisterParams): Flow<RequestResult<UserTokenDTO>> {
        return flow{ emit(api.register(userRegisterParams = userRegisterParams)) }.map { it.toRequestResult() }
    }
}