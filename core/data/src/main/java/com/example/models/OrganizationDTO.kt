package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class OrganizationDTO(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val description: String,
    val contactEmail: String?,
    val phoneNumber: String?,
    val websiteUrl: String?,
)
