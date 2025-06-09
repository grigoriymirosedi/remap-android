package com.example.core.api

import com.example.models.OrganizationDTO
import com.example.models.ProfileInfoDTO
import com.example.models.RecycleEventDTO
import com.example.models.RecyclePointDTO
import com.example.models.UserLoginParams
import com.example.models.UserRegisterParams
import com.example.models.UserTokenDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RemapAPI {

    @POST("v1/login")
    suspend fun login(@Body userLoginParams: UserLoginParams): Result<UserTokenDTO>

    @POST("v1/register")
    suspend fun register(@Body userRegisterParams: UserRegisterParams): Result<UserTokenDTO>

    @GET("v1/recycle-points")
    suspend fun getRecyclePoints(): Result<List<RecyclePointDTO>>

    @POST("v1/recycle-point")
    suspend fun addRecyclePoint(@Body recyclePointDTO: RecyclePointDTO)

    @GET("v1/fetchUserInfo")
    suspend fun getUserInfo(): Result<ProfileInfoDTO>

    @GET("v1/events")
    suspend fun getEvents(): Result<List<RecycleEventDTO>>

    @GET("v1/organizations")
    suspend fun getOrganizations(): Result<List<OrganizationDTO>>
}