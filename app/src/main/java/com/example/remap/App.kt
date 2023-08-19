package com.example.remap

import android.app.Application
import android.os.Build
import android.util.Log
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
    }
}