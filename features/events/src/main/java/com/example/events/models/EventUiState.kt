package com.example.events.models

data class EventUiState(
    val recycleEvents: List<RecycleEvent>
)

sealed class State {
    data object Loading: State()

    data class Success(val data: EventUiState): State()

    data class Error(val message: String): State()
}
