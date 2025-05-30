package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserTokenDTO(
    val token: String? = null
)
