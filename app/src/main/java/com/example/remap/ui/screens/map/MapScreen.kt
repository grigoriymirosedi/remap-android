package com.example.remap.ui.screens.map

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.remap.R
import com.example.remap.core.util.getBitmapFromVectorDrawable
import com.example.remap.ui.screens.calendar.MapViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import rememberMapViewWithLifecycle

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel(),
    onLoad: ((map: MapView) -> Unit)? = null) {

    val context = LocalContext.current

    MapKitFactory.initialize(context)

    val mapViewState = rememberMapViewWithLifecycle()

    val recyclePoints = viewModel.recyclePoints.collectAsState()

    val recyclePointCollection = mapViewState.mapWindow.map.mapObjects.addCollection()

    val bitmap = context.getBitmapFromVectorDrawable(R.drawable.ic_map_pin)

    val imageProvider = ImageProvider.fromBitmap(bitmap)

    AndroidView(
        {
            mapViewState.apply {
                mapWindow.map.apply {
                    move(
                        CameraPosition(Point(47.227188, 39.593677), 17.0f, 0.0f, 0.0f),
                    )
                }

                Log.d("123123", recyclePoints.value.toString())

                recyclePoints.value.forEach {
                    recyclePointCollection.addPlacemark().apply {
                        geometry = Point(it.latitude, it.longitude)
                        setIcon(imageProvider)
                    }
                }
            }
        },
        modifier
    ) { mapView -> onLoad?.invoke(mapView) }

}