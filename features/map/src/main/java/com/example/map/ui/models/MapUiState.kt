package com.example.map.ui.models

data class MapUiState(
    val recyclePoints: List<MapRecyclePointItem> = emptyList(),
    val categoryFilters: List<String> = emptyList()
)

sealed class State {
    data object Loading: State()

    data class Success(val data: MapUiState): State()

    data class Error(val message: String): State()
}

