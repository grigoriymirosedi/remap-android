package com.example.di

import com.example.data.remote.api.RemapRecycleAPI
import com.example.data.repository.RecyclePointRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRecyclePointRepository(api: RemapRecycleAPI): RecyclePointRepository {
        return RecyclePointRepository(remapRecycleAPI = api)
    }

}