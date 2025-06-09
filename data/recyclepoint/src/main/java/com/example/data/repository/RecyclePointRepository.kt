package com.example.data.repository

import com.example.utils.RequestResult
import com.example.models.RecyclePointDTO
import kotlinx.coroutines.flow.Flow

interface RecyclePointRepository {
    fun getRecyclePoints(): Flow<RequestResult<List<RecyclePointDTO>>>

    suspend fun addRecyclePoint(recyclePointDTO: RecyclePointDTO)
}