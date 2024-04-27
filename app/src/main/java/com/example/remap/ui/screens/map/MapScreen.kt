package com.example.remap.ui.screens.map

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remap.R
import com.example.remap.core.util.getBitmapFromVectorDrawable
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.ui.screens.calendar.MapViewModel
import com.example.remap.ui.screens.map.components.RecyclePointInfoBottomSheet
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import rememberMapViewWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel(),
    onLoad: ((map: MapView) -> Unit)? = null) {

    val context = LocalContext.current

    MapKitFactory.initialize(context)

    val mapViewState = rememberMapViewWithLifecycle()

    val recyclePoints = viewModel.recyclePoints.collectAsState()

    var recyclePointData by remember { mutableStateOf<RecyclePoint?>(null) }

    val recyclePointInfoBottomSheetState = rememberModalBottomSheetState()

    var showRecyclePointInfoBottomSheet by remember { mutableStateOf(false) }

    val recyclePointCollection = mapViewState.mapWindow.map.mapObjects.addCollection()

    val bitmap = context.getBitmapFromVectorDrawable(R.drawable.ic_map_pin)

    val imageProvider = ImageProvider.fromBitmap(bitmap)

    val inputListener = object : InputListener {
        override fun onMapTap(map: Map, point: Point) {
            Toast.makeText(context, "Single tap clicked", Toast.LENGTH_SHORT).show()
        }

        override fun onMapLongTap(map: Map, point: Point) {
            Toast.makeText(context, "Long tap clicked", Toast.LENGTH_SHORT).show()
        }
    }

    val mapObjectTapListener = object : MapObjectTapListener {
        override fun onMapObjectTap(mapObject: MapObject, point: Point): Boolean {
            recyclePointData = mapObject.userData as RecyclePoint
            showRecyclePointInfoBottomSheet = true
            return true
        }
    }

    if(showRecyclePointInfoBottomSheet) {
        RecyclePointInfoBottomSheet(
            recyclePoint = recyclePointData!!,
            bottomSheetState = recyclePointInfoBottomSheetState,
            onDismissRequest = { showRecyclePointInfoBottomSheet = false }
        )
    }

    AndroidView(
        {
            mapViewState.apply {
                mapWindow.map.apply {
                    move(
                        CameraPosition(Point(47.227188, 39.593677), 17.0f, 0.0f, 0.0f),
                    )
                }.addInputListener(inputListener)
            }
        },
        modifier
    ) {

        mapView -> onLoad?.invoke(mapView)

        recyclePoints.value.forEach {
            recyclePointCollection.addPlacemark().apply {
                geometry = Point(it.latitude, it.longitude)
                userData = it
                setIcon(imageProvider)
            }.addTapListener(mapObjectTapListener)
        }
    }
}