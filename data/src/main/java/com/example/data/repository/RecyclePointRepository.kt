package com.example.data.repository

import com.example.data.remote.api.RemapRecycleAPI
import com.example.data.remote.dto.RecyclePointDTO
import retrofit2.Response
import javax.inject.Inject

class RecyclePointRepository @Inject constructor(private val remapRecycleAPI: RemapRecycleAPI) {
    suspend fun getRecyclePoints(categoryType: List<String>?): Response<List<RecyclePointDTO>> {
        return remapRecycleAPI.getRecyclePoints(categoryType = categoryType)
    }

    suspend fun addRecyclePoint(categoryType: List<String>, recyclePointDTO: RecyclePointDTO): Response<RecyclePointDTO> {
        return remapRecycleAPI.addRecyclePoint(categoryType = categoryType, recyclePointDTO = recyclePointDTO)
    }
}