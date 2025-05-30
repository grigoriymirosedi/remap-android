package com.example

import com.example.models.RecycleEventDTO
import com.example.utils.RequestResult
import kotlinx.coroutines.flow.Flow

interface RecycleEventRepository {
    fun getEvents(): Flow<RequestResult<List<RecycleEventDTO>>>
}