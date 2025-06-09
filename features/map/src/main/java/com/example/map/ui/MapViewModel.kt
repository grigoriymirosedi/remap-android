package com.example.map.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.RecyclePointRepository
import com.example.map.ui.models.MapRecyclePointItem
import com.example.map.ui.models.MapUiState
import com.example.map.ui.models.State
import com.example.map.ui.models.toMapRecyclePointItem
import com.example.models.RecyclePointDTO
import com.example.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MapEvent {
    data object LoadRecyclePoints : MapEvent
    data class CreateDummyRecyclePoint(val latitude: Double, val longitude: Double) : MapEvent
    data class AddCategoryFilter(val name: String) : MapEvent
    data object DeleteDummyRecyclePoint : MapEvent
}

@HiltViewModel
class MapViewModel @Inject constructor(
    private val recyclePointRepository: RecyclePointRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val uiState: StateFlow<State> = _uiState

    fun handleEvent(event: MapEvent) {
        when (event) {
            is MapEvent.LoadRecyclePoints -> loadRecyclePoints()
            is MapEvent.CreateDummyRecyclePoint -> createDummyRecyclePoint(
                latitude = event.latitude,
                longitude = event.longitude
            )

            is MapEvent.AddCategoryFilter -> addCategoryFilter(
                filterName = event.name
            )

            is MapEvent.DeleteDummyRecyclePoint -> deleteDummyRecyclePoint()
        }
    }

    private fun loadRecyclePoints() {
        viewModelScope.launch {
            _uiState.update {
                recyclePointRepository.getRecyclePoints().map { result ->
                    State.Success(MapUiState(result.toMapRecyclePointItem()))
                }.single()
            }
        }
    }

    private fun addCategoryFilter(filterName: String) {
        val currentState = _uiState.value
        if (currentState is State.Success) {
            if (currentState.data.categoryFilters.contains(filterName)) {
                val updatedCategoryFilters = currentState.data.categoryFilters.filter { it != filterName }
                _uiState.update {
                    State.Success(
                        currentState.data.copy(
                            categoryFilters = updatedCategoryFilters
                        )
                    )
                }
            } else {
                val updatedCategoryFilters = currentState.data.categoryFilters + filterName
                _uiState.update {
                    State.Success(
                        currentState.data.copy(
                            categoryFilters = updatedCategoryFilters
                        )
                    )
                }

            }
        }
    }

    private fun createDummyRecyclePoint(latitude: Double, longitude: Double) {
        val dummyRecyclePoint = MapRecyclePointItem(
            name = "",
            description = "",
            address = "",
            locationHint = null,
            latitude = latitude,
            longitude = longitude,
            acceptedItems = emptyList(),
            workingHours = "",
            phoneNumber = null,
            imageUrl = null,
            moderationStatus = 0,
            isDummy = true
        )
        val currentState = _uiState.value
        if (currentState is State.Success) {
            val newRecyclePointData =
                currentState.data.recyclePoints.filter { !it.isDummy } + dummyRecyclePoint
            _uiState.value = State.Success(
                MapUiState(
                    recyclePoints = newRecyclePointData
                )
            )
        }
    }

    private fun deleteDummyRecyclePoint() {
        val currentState = _uiState.value
        if (currentState is State.Success) {
            val updatedRecyclePointData = currentState.data.recyclePoints.dropLast(n = 1)
            _uiState.value = State.Success(
                MapUiState(
                    recyclePoints = updatedRecyclePointData
                )
            )
        }
    }

}

fun RequestResult<List<RecyclePointDTO>>.toMapRecyclePointItem(): List<MapRecyclePointItem> {
    return if (this is RequestResult.Success) {
        data?.map { it.toMapRecyclePointItem() } ?: emptyList()
    } else emptyList()
}