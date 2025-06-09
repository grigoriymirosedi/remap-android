package com.example.map.ui.components

import android.content.Context
import android.util.Log
import com.example.core.util.getBitmapFromVectorDrawable
import com.example.map.ui.models.MapRecyclePointItem
import com.example.ui.R
import com.example.util.DEFAULT_AZIMUTH
import com.example.util.DEFAULT_TILT
import com.example.util.DEFAULT_ZOOM
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.SearchType
import com.yandex.mapkit.search.Session
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider

class RecyclePointMapView(
    context: Context,
    val latitude: Double,
    val longitude: Double,
    val recyclePoints: List<MapRecyclePointItem>,
    var categoryFilters: List<String>,
    val zoom: Float = DEFAULT_ZOOM,
    val azimuth: Float = DEFAULT_AZIMUTH,
    val tilt: Float = DEFAULT_TILT,
    onRecyclePointClick: (MapRecyclePointItem) -> Unit,
    onMapLongTap: (Double, Double) -> Unit,
    onMapClick: () -> Unit,
    onSearchResult: (String) -> Unit,
) : MapView(context) {

    private val drawableIcon = context.getBitmapFromVectorDrawable(R.drawable.ic_map_pin)

    private var dummyRecyclePoint: MapObject? = null

    private val searchManager =
        SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE)

    private val mapObjectList = mutableListOf<MapObject>()

    private val searchOptions = SearchOptions().apply {
        resultPageSize = 1
        searchTypes = SearchType.GEO.value
    }

    private val mapObjectListener = MapObjectTapListener { mapObject, point ->
        val recyclePoint = mapObject.userData as MapRecyclePointItem
        onRecyclePointClick(recyclePoint)
        true
    }

    private val searchListener = object : Session.SearchListener {
        override fun onSearchResponse(p0: Response) {
            val address = p0.collection.children.firstOrNull()?.obj?.geometry?.firstOrNull()?.point
            val name = p0.collection.children.firstOrNull()?.obj?.name
            onSearchResult(name ?: "")
        }

        override fun onSearchError(p0: Error) {
            Log.e("yandex mapkit error", "OnSearchError invoked")
        }
    }

    private val mapOnClickListener = object : InputListener {
        override fun onMapTap(p0: Map, p1: Point) {
            onMapClick()
        }

        override fun onMapLongTap(p0: Map, p1: Point) {
            onMapLongTap(p1.latitude, p1.longitude)
            searchManager.submit(p1, 16, searchOptions, searchListener)
        }
    }

    init {
        mapWindow.map.apply {
            move(CameraPosition(Point(latitude, longitude), zoom, azimuth, tilt))
        }
        mapWindow.map.mapObjects.addTapListener(mapObjectListener)
        mapWindow.map.addInputListener(mapOnClickListener)
        loadRecyclePoints()
    }

    fun updateCategoryFilters(categoryFilters: List<String>) {
        this.categoryFilters = categoryFilters
        if (categoryFilters.isEmpty()) {
            mapObjectList.forEach { mapObject ->
                mapObject.isVisible = true
            }
        } else {
            mapObjectList.forEach { mapObject ->
                val recyclePointObject = mapObject.userData as MapRecyclePointItem
                mapObject.isVisible = categoryFilters.any { it in recyclePointObject.acceptedItems }
            }
        }
    }

    fun updateRecyclePoints(newPoints: List<MapRecyclePointItem>) {
        dummyRecyclePoint?.let { placemark ->
            mapWindow.map.mapObjects.remove(placemark)
            dummyRecyclePoint = null
        }

        val dummyPoint = newPoints.lastOrNull { it.isDummy }
        if (dummyPoint != null) {
            dummyRecyclePoint = mapWindow.map.mapObjects.addPlacemark().apply {
                geometry = Point(dummyPoint.latitude, dummyPoint.longitude)
                setIcon(ImageProvider.fromBitmap(drawableIcon))
            }
        }
    }

    private fun loadRecyclePoints() {
        recyclePoints.forEach { recyclePoint ->
            mapObjectList.add(
                mapWindow.map.mapObjects.addPlacemark().apply {
                    geometry = Point(recyclePoint.latitude, recyclePoint.longitude)
                    setIcon(ImageProvider.fromBitmap(drawableIcon))
                    userData = recyclePoint
                }
            )
        }
    }
}