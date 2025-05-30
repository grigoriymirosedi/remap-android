package com.example.map.ui

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val recyclePointRepository: RecyclePointRepository,
) : ViewModel() {

    val uiState = recyclePointRepository.getRecyclePoints().map {
        State.Success(MapUiState(recyclePoints = it.toMapRecyclePointItem()))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State.Loading)
}

fun RequestResult<List<RecyclePointDTO>>.toMapRecyclePointItem(): List<MapRecyclePointItem> {
    return if(this is RequestResult.Success) {
        data?.map { it.toMapRecyclePointItem() } ?: emptyList()
    } else emptyList()
}