package com.example.map.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.dto.RecyclePointDTO
import com.example.data.remote.dto.toRecyclePoint
import com.example.core.models.RecyclePoint
import com.example.data.repository.RecyclePointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: RecyclePointRepository,
) : ViewModel() {

    val categoryType = MutableStateFlow(mutableListOf<String>())

    val filterCategories = mutableListOf<String>()

    private val _recyclePoints = MutableStateFlow<List<RecyclePoint>>(emptyList())
    var recyclePoints: StateFlow<List<RecyclePoint>> = _recyclePoints

    init {
        initRecyclePoint()
    }

    fun addRecyclePoint(
        recyclePointDTO: com.example.data.remote.dto.RecyclePointDTO
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.addRecyclePoint(categoryType = categoryType.value.toList(), recyclePointDTO = recyclePointDTO)
            val responseBody = response.body()!!.toRecyclePoint()
            _recyclePoints.value += responseBody
        }
    }

    fun updateRecyclePoints() {
        val categoryType = if(filterCategories.isEmpty()) null else filterCategories
        viewModelScope.launch {
            val response = repository.getRecyclePoints(categoryType = categoryType)
            val handleResponse = response.body()?.map { it.toRecyclePoint() } ?: emptyList()
            _recyclePoints.value = emptyList()
            _recyclePoints.value += handleResponse
        }
    }

    private fun initRecyclePoint() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.getRecyclePoints(categoryType = null)
            }.onSuccess {
                val handleResponse = it.body()?.map { it.toRecyclePoint() } ?: emptyList()
                _recyclePoints.value += handleResponse;
            }.onFailure {
                _recyclePoints.value = emptyList();
            }
        }
    }
}