package com.example.remap.core

import android.app.Application
import com.example.remap.BuildConfig
import com.yandex.mapkit.MapKitFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
    }
}