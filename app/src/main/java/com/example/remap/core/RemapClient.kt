package com.example.remap.core

import com.example.remap.core.util.Constants
import com.example.remap.data.remote.api.RemapRecycleAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemapClient {

    private val retrofit by lazy {
        var logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder().
            baseUrl(Constants.BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            client(client).
            build()
    }
    fun provideRemapRecycleAPI(): RemapRecycleAPI = retrofit.create(RemapRecycleAPI::class.java)

}