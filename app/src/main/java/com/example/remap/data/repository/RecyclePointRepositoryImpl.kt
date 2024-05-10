package com.example.remap.data.repository

import com.example.remap.data.remote.api.RemapRecycleAPI
import com.example.remap.data.remote.dto.CalendarEventDTO
import com.example.remap.data.remote.dto.RecyclePointDTO
import com.example.remap.domain.repository.RecyclePointRepository
import retrofit2.Response
import javax.inject.Inject

class RecyclePointRepositoryImpl @Inject constructor(private val remapRecycleAPI: RemapRecycleAPI): RecyclePointRepository {
    override suspend fun getRecyclePoints(categoryType: List<String>?): Response<List<RecyclePointDTO>> {
        return remapRecycleAPI.getRecyclePoints(categoryType = categoryType)
    }

    override suspend fun getEvents(): Response<List<CalendarEventDTO>> {
        return remapRecycleAPI.getEvents()
    }

    override suspend fun addRecyclePoint(categoryType: List<String>, recyclePointDTO: RecyclePointDTO): Response<RecyclePointDTO> {
        return remapRecycleAPI.addRecyclePoint(categoryType = categoryType, recyclePointDTO = recyclePointDTO)
    }
}