package com.example.di

import android.content.Context
import android.media.session.MediaSession.Token
import androidx.core.content.contentValuesOf
import com.example.util.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    fun provideTokenManger(@ApplicationContext appContext: Context): TokenManager {
        return TokenManager(context = appContext)
    }
}