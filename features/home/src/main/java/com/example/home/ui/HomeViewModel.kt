package com.example.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ProfileRepository
import com.example.RecycleEventRepository
import com.example.data.repository.RecyclePointRepository
import com.example.home.models.HomeUiState
import com.example.home.models.ProfileInfo
import com.example.home.models.RecycleEvent
import com.example.home.models.RecyclePoint
import com.example.home.models.RequestItem
import com.example.home.models.State
import com.example.home.models.convertToCollectedItem
import com.example.home.models.toProfileInfo
import com.example.home.models.toRecycleEvent
import com.example.home.models.toRecyclePoints
import com.example.models.ProfileInfoDTO
import com.example.models.RecycleEventDTO
import com.example.models.RecyclePointDTO
import com.example.models.RequestItemDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface HomeUiEvent {
    data class navigateToMap(val recyclePoint: RecyclePoint): HomeUiEvent
    data class toggleUpcomingEventDetails(val event: RecycleEvent): HomeUiEvent
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recyclePointRepository: RecyclePointRepository,
    private val profileRepository: ProfileRepository,
    private val eventRepository: RecycleEventRepository,
) : ViewModel() {

    private val recyclePointsFlow = recyclePointRepository.getRecyclePoints()
        .map {
            it.toRecyclePoints()
        }.catch { emit(emptyList()) }

    private val userProfileFlow = profileRepository.getUserInfo()
        .map {
            it.toProfileInfo()
        }

    private val recycleEventsFlow = eventRepository.getEvents()
        .map {
            it.toRecycleEvent()
        }

    val uiState = combine(
        recyclePointsFlow,
        userProfileFlow,
        recycleEventsFlow,
    ) { recyclePoints, userProfile, recycleEvents ->
        HomeUiState(
            profileInfo = userProfile,
            recyclePointsNearby = recyclePoints,
            upcomingEvents = recycleEvents,
        )
    }.map { state ->
        if(!state.isEmpty())
            State.Success(state)
        else
            State.Error("An error occured. Check the internet connection.")
    }.catch { e ->
        State.Error(e.message ?: "Unexpected error")
    }.stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    fun handleEvent(event: HomeUiEvent) {
        when(event) {
            is HomeUiEvent.toggleUpcomingEventDetails -> toggleUpcomingEvent(event.event)
            else -> Unit
        }
    }

    private fun toggleUpcomingEvent(evet: RecycleEvent) {

    }
}

fun HomeUiState.isEmpty() = collectedItems.isEmpty() && recyclePointsNearby.isEmpty()

fun RecycleEventDTO.toRecycleEvent() = RecycleEvent(
    id = id,
    title = title,
    description = description,
    date = date.toString(),
    time = time.toString(),
    location = location,
    imageUrl = imageUrl ?: ""
)

fun ProfileInfoDTO.toProfileInfo() = ProfileInfo(
    username = username,
    email = email,
    points = points,
    tip = tip,
    requests = requests.map { it.toRequestItem() },
    collectedItems = this.convertToCollectedItem()
)

fun RequestItemDTO.toRequestItem() = RequestItem(
    requestNumber = requestNumber,
    title = title,
    status = status
)

fun RecyclePointDTO.toRecyclePoint() = RecyclePoint(
    id = id,
    name = name,
    address = address,
    latitude = latitude,
    longitude = longitude,
    acceptedItems = emptyList(),
    workingHours = workingHours,
    phoneNumber = phoneNumber,
    imageUrl = imageUrl
)