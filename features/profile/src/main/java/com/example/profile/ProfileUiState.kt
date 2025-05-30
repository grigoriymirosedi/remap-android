package com.example.profile

sealed interface ProfileUiState {
    data object Error : ProfileUiState
    data object Loading : ProfileUiState
    data class Success(
        val username: String,
        val email: String,
        val totallyCollected: Int,
        val userPoints: Int,
        val requestsCount: Int,
    ) : ProfileUiState
}