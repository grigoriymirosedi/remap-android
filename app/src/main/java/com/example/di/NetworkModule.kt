package com.example.di

import com.example.core.api.RemapAPI
import com.example.util.Constants
import com.example.util.TokenManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRemapAPI(retrofit: Retrofit): RemapAPI {
        return retrofit.create(RemapAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        loggingClient: OkHttpClient,
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .client(loggingClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(tokenManager: TokenManager): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val token = tokenManager.getToken()
                val newRequest = if (!token.isNullOrEmpty()) {
                    request.newBuilder()
                        .header("Authorization", "Bearer $token")
                        .build()
                } else {
                    request
                }

                chain.proceed(newRequest)
            }
            .addInterceptor(logging)
            .build()
    }
}