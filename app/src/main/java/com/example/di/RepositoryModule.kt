package com.example.di

import com.example.ProfileRepository
import com.example.ProfileRepositoryImpl
import com.example.RecycleEventRepository
import com.example.RecycleEventRepositoryImpl
import com.example.auth.AuthRepository
import com.example.auth.AuthRepositoryImpl
import com.example.core.api.RemapAPI
import com.example.data.OrganizationsRepository
import com.example.data.OrganizationsRepositoryImpl
import com.example.data.repository.RecyclePointRepository
import com.example.data.repository.RecyclePointRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRecyclePointRepository(api: RemapAPI): RecyclePointRepository {
        return RecyclePointRepositoryImpl(api)
    }

    @Provides
    fun provideProfileRepository(api: RemapAPI): ProfileRepository {
        return ProfileRepositoryImpl(api)
    }

    @Provides
    fun provideRecycleEventRepository(api: RemapAPI): RecycleEventRepository {
        return RecycleEventRepositoryImpl(api)
    }

    @Provides
    fun provideOrganizationsRepository(api: RemapAPI): OrganizationsRepository {
        return OrganizationsRepositoryImpl(api)
    }

    @Provides
    fun provideAuthRepository(api: RemapAPI): AuthRepository {
        return AuthRepositoryImpl(api)
    }

}