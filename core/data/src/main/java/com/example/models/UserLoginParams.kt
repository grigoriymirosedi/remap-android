package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginParams(
    val username: String,
    val password: String
)
