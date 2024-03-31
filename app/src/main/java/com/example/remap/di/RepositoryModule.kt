package com.example.remap.di

import com.example.remap.data.remote.api.RemapRecycleAPI
import com.example.remap.data.repository.RecyclePointRepositoryImpl
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.domain.repository.RecyclePointRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRecyclePointRepository(api: RemapRecycleAPI): RecyclePointRepository {
        return RecyclePointRepositoryImpl(remapRecycleAPI = api)
    }

}