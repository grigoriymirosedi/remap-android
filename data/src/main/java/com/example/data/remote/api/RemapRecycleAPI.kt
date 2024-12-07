package com.example.data.remote.api

import com.example.data.remote.dto.RecyclePointDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RemapRecycleAPI {
    @GET("v1/recycle-points")
    suspend fun getRecyclePoints(@Query("categoryType") categoryType: List<String>?): Response<List<RecyclePointDTO>>

    @POST("v1/recycle-points")
    suspend fun addRecyclePoint(@Query("categoryType") categoryType: List<String>, @Body recyclePointDTO: RecyclePointDTO): Response<RecyclePointDTO>

}