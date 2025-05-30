package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterParams(
    val username: String,
    val email: String,
    val password: String
)
