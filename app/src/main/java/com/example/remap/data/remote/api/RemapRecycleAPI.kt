package com.example.remap.data.remote.api

import com.example.remap.data.remote.dto.CalendarEventDTO
import com.example.remap.data.remote.dto.RecyclePointDTO
import retrofit2.Response
import retrofit2.http.GET

interface RemapRecycleAPI {
    @GET("v1/recycle-points")
    suspend fun getRecyclePoints(): Response<List<RecyclePointDTO>>

    @GET("v1/events")
    suspend fun getEvents(): Response<List<CalendarEventDTO>>

}