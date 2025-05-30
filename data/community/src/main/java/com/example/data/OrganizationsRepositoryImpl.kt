package com.example.data

import com.example.core.api.RemapAPI
import com.example.models.OrganizationDTO
import com.example.utils.RequestResult
import com.example.utils.toRequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrganizationsRepositoryImpl @Inject constructor(val api: RemapAPI): OrganizationsRepository {
    override fun getOrganizations(): Flow<RequestResult<List<OrganizationDTO>>> = flow { emit(api.getOrganizations())}
        .map { it.toRequestResult() }
}