package com.example.map.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.map.ui.models.MapRecyclePointItem
import com.example.util.DEFAULT_AZIMUTH
import com.example.util.DEFAULT_TILT
import com.example.util.DEFAULT_ZOOM
import com.example.util.INIT_LATITUDE
import com.example.util.INIT_LONGITUDE
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

@Composable
fun YandexMapView(
    modifier: Modifier = Modifier,
    recyclePoints: List<MapRecyclePointItem>,
    latitude: Double = INIT_LATITUDE,
    longitude: Double = INIT_LONGITUDE,
    zoom: Float = DEFAULT_ZOOM,
    azimuth: Float = DEFAULT_AZIMUTH,
    tilt: Float = DEFAULT_TILT,
    onRecyclePointClick: (MapRecyclePointItem) -> Unit,
    onMapClick: () -> Unit,
    onMapLongTap: () -> Unit,
) {
    val context = LocalContext.current
    val mapView = remember {
        mutableStateOf<MapView?>(null)
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            RecyclePointMapView(
                context = context,
                latitude = latitude,
                longitude = longitude,
                recyclePoints = recyclePoints,
                onRecyclePointClick = onRecyclePointClick,
                onMapClick = onMapClick,
                onMapLongTap = onMapLongTap
            ).also { mapView.value = it }
        }
    ) {
        mapView.value = it
    }

    LaunchedEffect(key1 = Unit) {
        snapshotFlow { mapView.value }.collect {
            it?.let {
                MapKitFactory.initialize(context)
                MapKitFactory.getInstance()
                it.onStart()
            }
        }
    }
}