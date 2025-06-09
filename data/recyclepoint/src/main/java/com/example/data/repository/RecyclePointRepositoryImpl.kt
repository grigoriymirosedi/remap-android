package com.example.data.repository

import com.example.core.api.RemapAPI
import com.example.utils.RequestResult
import com.example.models.RecyclePointDTO
import com.example.utils.toRequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecyclePointRepositoryImpl @Inject constructor(private val remapAPI: RemapAPI): RecyclePointRepository {
    override fun getRecyclePoints(): Flow<RequestResult<List<RecyclePointDTO>>> {
        return flow {
            try {
                val points = remapAPI.getRecyclePoints()
                if(points.isSuccess) {
                    val points = points.toRequestResult()
                    emit(points)
                }

            } catch (e: Exception) {
                println("response type" + e.toString())
                emit(RequestResult.Error(e.message ?: "Unknown error"))
            }
        }
    }

    override suspend fun addRecyclePoint(recyclePointDTO: RecyclePointDTO) {
        remapAPI.addRecyclePoint(recyclePointDTO = recyclePointDTO)
    }
}