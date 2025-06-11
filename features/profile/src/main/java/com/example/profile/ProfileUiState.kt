package com.example.profile

import com.example.profile.requests.RequestItem

sealed interface ProfileUiState {
    data object Error : ProfileUiState
    data object Loading : ProfileUiState
    data class Success(
        val username: String,
        val email: String,
        val totallyCollected: Int,
        val requests: List<RequestItem>,
        val userPoints: Int,
        val requestsCount: Int,
    ) : ProfileUiState
}