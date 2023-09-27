package com.example.remap.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remap.core.util.Resource
import com.example.remap.data.remote.dto.RecyclePointDTO
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.domain.repository.RecyclePointRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MapFragmentViewModel(private val recyclePointRepository: RecyclePointRepository): ViewModel() {

    var recyclePointResponse: MutableLiveData<Resource<List<RecyclePoint>>> = MutableLiveData()

    init {
        getRecyclePointInfo()
    }

    private fun getRecyclePointInfo() = viewModelScope.launch {
        val response = recyclePointRepository.getRecyclePoints()
        recyclePointResponse.postValue(handleRecyclePointResponse(response))
    }

    private fun handleRecyclePointResponse(response: Response<List<RecyclePointDTO>>): Resource<List<RecyclePoint>> {
        if (response.isSuccessful) {
            response.body()?.let {  resultResponse ->
                return Resource.Success(resultResponse.map { it.toRecyclePoint() } )
            }
        }
        return Resource.Error(response.message())
    }
}