package com.example.remap.ui.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remap.data.remote.dto.RecyclePointDTO
import com.example.remap.data.remote.dto.toRecyclePoint
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.domain.repository.RecyclePointRepository
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

    private val _recyclePoints = MutableStateFlow(listOf<RecyclePoint>())
    val recyclePoints: StateFlow<List<RecyclePoint>> = _recyclePoints

    init {
        initRecyclePoint()
    }

    fun addRecyclePoint(
        name: String,
        image: String?,
        description: String,
        contacts: String,
        latitude: Double,
        longitude: Double,
        address: String,
        working_hours: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val recyclePointRequest = RecyclePointDTO(
                name = name,
                image = image,
                description = description,
                contacts = contacts,
                latitude = latitude,
                longitude = longitude,
                address = address,
                working_hours = working_hours
            )
            repository.addRecyclePoint(categoryType = categoryType.value.toList(), recyclePointDTO = recyclePointRequest)
        }
    }

    private fun initRecyclePoint() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.getRecyclePoints()
            }.onSuccess {
                val handleResponse = it.body()?.map { it.toRecyclePoint() } ?: emptyList()
                _recyclePoints.emit(value = handleResponse)
            }.onFailure {
                _recyclePoints.emit(value = emptyList())
            }
        }
    }
}