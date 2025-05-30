package com.example.home.models

data class HomeUiState(
    val profileInfo: ProfileInfo = ProfileInfo(),
    val collectedItems: List<CollectedItem> = emptyList(),
    val recyclePointsNearby: List<RecyclePoint> = emptyList(),
    val upcomingEvents: List<RecycleEvent> = emptyList(),
)

sealed class State {
    data object Loading: State()

    data class Success(val data: HomeUiState): State()

    data class Error(val message: String): State()
}
