package com.example.remap.ui.screens.map

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import rememberMapViewWithLifecycle

@Composable
fun MapScreen(modifier: Modifier = Modifier, onLoad: ((map: MapView) -> Unit)? = null) {

    val context = LocalContext.current

    MapKitFactory.initialize(context)

    val mapViewState = rememberMapViewWithLifecycle()

    AndroidView(
        {
            mapViewState.apply {
                mapWindow.map.apply {
                    move(
                        CameraPosition(Point(47.227188, 39.593677), 17.0f, 0.0f, 0.0f),
                    )
                }
            }
        },
        modifier
    ) { mapView -> onLoad?.invoke(mapView) }

}