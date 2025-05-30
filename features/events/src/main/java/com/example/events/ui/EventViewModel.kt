package com.example.events.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.RecycleEventRepository
import com.example.events.models.EventUiState
import com.example.events.models.RecycleEvent
import com.example.events.models.State
import com.example.events.models.toRecycleEvent
import com.example.models.RecycleEventDTO
import com.example.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val recycleEventRepository: RecycleEventRepository
): ViewModel() {
    val uiState = recycleEventRepository.getEvents().map {
        State.Success(EventUiState(recycleEvents = it.toMapRecyclePointItem()))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State.Loading)
}
fun RequestResult<List<RecycleEventDTO>>.toMapRecyclePointItem(): List<RecycleEvent> {
    return if(this is RequestResult.Success) {
        data?.map { it.toRecycleEvent() } ?: emptyList()
    } else emptyList()
}