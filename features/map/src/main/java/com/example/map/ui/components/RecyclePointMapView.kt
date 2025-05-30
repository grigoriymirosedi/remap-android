package com.example.map.ui.components

import android.content.Context
import android.util.Log
import com.example.core.util.getBitmapFromVectorDrawable
import com.example.map.ui.models.MapRecyclePointItem
import com.example.ui.R
import com.example.util.DEFAULT_AZIMUTH
import com.example.util.DEFAULT_TILT
import com.example.util.DEFAULT_ZOOM
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.runtime.image.ImageProvider

class RecyclePointMapView(
    context: Context,
    val latitude: Double,
    val longitude: Double,
    val recyclePoints: List<MapRecyclePointItem>,
    val zoom: Float = DEFAULT_ZOOM,
    val azimuth: Float = DEFAULT_AZIMUTH,
    val tilt: Float = DEFAULT_TILT,
    onRecyclePointClick: (MapRecyclePointItem) -> Unit,
    onMapLongTap: () -> Unit,
    onMapClick: () -> Unit
): MapView(context) {

    private val drawableIcon = context.getBitmapFromVectorDrawable(R.drawable.ic_map_pin)

    private val mapObjectListener = MapObjectTapListener { mapObject, point ->
        val recyclePoint = mapObject.userData as MapRecyclePointItem
        onRecyclePointClick(recyclePoint)
        true
    }

    private val mapOnClickListener = object: InputListener {
        override fun onMapTap(p0: Map, p1: Point) {
            onMapClick()
        }

        override fun onMapLongTap(p0: Map, p1: Point) {
            onMapLongTap()
        }
    }

    init {
        mapWindow.map.apply {
            move(CameraPosition(Point(latitude, longitude), zoom , azimuth, tilt))
        }
        mapWindow.map.mapObjects.addTapListener(mapObjectListener)
        mapWindow.map.addInputListener(mapOnClickListener)
        loadRecyclePoints()
    }

    private fun loadRecyclePoints() {
        recyclePoints.forEach { recyclePoint ->
            mapWindow.map.mapObjects.addPlacemark().apply {
                geometry = Point(recyclePoint.latitude, recyclePoint.longitude)
                setIcon(ImageProvider.fromBitmap(drawableIcon))
                userData = recyclePoint
            }
        }
    }
}