package com.example.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    val uiState: StateFlow<ProfileUiState> = profileRepository.getUserInfo().map {
        if (it.data != null) {
            ProfileUiState.Success(
                username = it.data?.username ?: "",
                email = it.data?.email ?: "",
                userPoints = it.data?.points ?: 0,
                requestsCount = it.data?.requests?.size ?: 0,
                totallyCollected = it.data?.let { items ->
                    items.collectedUnits[0].batteriesUnit + items.collectedUnits[0].paperUnit + items.collectedUnits[0].plasticUnit
                } ?: 0
            )
        } else {
            ProfileUiState.Error
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        ProfileUiState.Loading
    )
}