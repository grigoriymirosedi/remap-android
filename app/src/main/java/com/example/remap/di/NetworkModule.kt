package com.example.remap.di

import com.example.remap.core.util.Constants
import com.example.remap.data.remote.api.RemapRecycleAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRemapAPI(retrofit: Retrofit): RemapRecycleAPI {
        return retrofit.create(RemapRecycleAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        converter: Converter.Factory,
        loggingClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(converter)
            .client(loggingClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }
}