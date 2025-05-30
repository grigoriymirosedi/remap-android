package com.example.data

import com.example.models.OrganizationDTO
import com.example.utils.RequestResult
import kotlinx.coroutines.flow.Flow

interface OrganizationsRepository {
    fun getOrganizations(): Flow<RequestResult<List<OrganizationDTO>>>
}