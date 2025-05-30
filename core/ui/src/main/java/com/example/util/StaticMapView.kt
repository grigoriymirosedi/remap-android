package com.example.core.util

import android.content.Context
import com.yandex.mapkit.mapview.MapView

class StaticMapView(context: Context): MapView(context) {
    init {
        disableGestures()
    }

    private fun disableGestures() {
        mapWindow.map.apply {
            isRotateGesturesEnabled = false
            isScrollGesturesEnabled = false
            isTiltGesturesEnabled = false
            isZoomGesturesEnabled = false
        }
    }
}