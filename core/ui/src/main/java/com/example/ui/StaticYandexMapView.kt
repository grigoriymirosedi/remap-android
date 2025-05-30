package com.example.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.core.util.StaticMapView
import com.example.core.util.getBitmapFromVectorDrawable
import com.example.ui.R
import com.example.util.DEFAULT_AZIMUTH
import com.example.util.DEFAULT_TILT
import com.example.util.DEFAULT_ZOOM
import com.example.util.INIT_LATITUDE
import com.example.util.INIT_LONGITUDE
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun StaticYandexMapView (
    modifier: Modifier = Modifier,
    latitude: Double = INIT_LATITUDE,
    longitude: Double = INIT_LONGITUDE,
    zoom: Float = DEFAULT_ZOOM,
    azimuth: Float = DEFAULT_AZIMUTH,
    tilt: Float = DEFAULT_TILT,
) {
    val context = LocalContext.current
    val mapView = remember {
        mutableStateOf<MapView?>(null)
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            StaticMapView(context).apply {
                mapWindow.map.move (
                    CameraPosition(Point(latitude, longitude), zoom, azimuth, tilt)
                )
                val drawableIcon = context.getBitmapFromVectorDrawable(R.drawable.ic_map_pin)
                mapWindow.map.mapObjects.addPlacemark().apply {
                    geometry = Point(latitude, longitude)
                       setIcon(ImageProvider.fromBitmap(drawableIcon))
                }
            }
        }
    ) {
        mapView.value = it
    }

    LaunchedEffect(key1 = "loadStaticMapView") {
        snapshotFlow { mapView.value }.collect {
            it?.let {
                MapKitFactory.initialize(context)
                MapKitFactory.getInstance().onStart()
                it.onStart()
            }
        }
    }
}