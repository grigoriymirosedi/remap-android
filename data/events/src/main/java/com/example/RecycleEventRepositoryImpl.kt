package com.example

import com.example.core.api.RemapAPI
import com.example.models.RecycleEventDTO
import com.example.utils.RequestResult
import com.example.utils.toRequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecycleEventRepositoryImpl @Inject constructor(val api: RemapAPI): RecycleEventRepository {
    override fun getEvents(): Flow<RequestResult<List<RecycleEventDTO>>> = flow { emit(api.getEvents()) }
        .map{ it.toRequestResult() }
}