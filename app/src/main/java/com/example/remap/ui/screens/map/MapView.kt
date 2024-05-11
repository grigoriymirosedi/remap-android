package com.example.remap.ui.screens.map

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.remap.R
import com.example.remap.core.util.getBitmapFromVectorDrawable
import com.example.remap.domain.models.PlacemarkDetails
import com.example.remap.domain.models.RecyclePoint
import com.example.remap.ui.screens.calendar.MapViewModel
import com.example.remap.ui.screens.map.components.AddRecyclePontBottomSheet
import com.example.remap.ui.screens.map.components.PlaceMarkDetailsBottomSheet
import com.example.remap.ui.screens.map.components.RecyclePointInfoBottomSheet
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Address
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session.SearchListener
import com.yandex.mapkit.search.ToponymObjectMetadata
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.launch
import rememberMapViewWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapView(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel,
    onLoad: ((map: MapView) -> Unit)? = null
) {

    val context = LocalContext.current

    MapKitFactory.initialize(context)

    val searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE)

    var placeMarkDetails: PlacemarkDetails? by remember { mutableStateOf(null) }

    val mapViewState = rememberMapViewWithLifecycle()

    val recyclePoints = viewModel.recyclePoints

    val coroutineScope = rememberCoroutineScope()

    var tappedPlaceMark: MapObject? by remember { mutableStateOf(null) }

    var recyclePointData by remember { mutableStateOf<RecyclePoint?>(null) }

    val recyclePointInfoBottomSheetState = rememberModalBottomSheetState()

    var showRecyclePointInfoBottomSheet by remember { mutableStateOf(false) }

    val placemarkDetailsBottomSheet = rememberModalBottomSheetState()

    var tappedCoordinates by remember { mutableStateOf<Point?>(null) }

    var showPlaceMarkDetailsInfoBottomSheet by remember { mutableStateOf(false) }

    var showAddRecyclePointBottomSheet by remember { mutableStateOf(false) }

    val addRecyclePointBottomSheet = rememberModalBottomSheetState()

    val recyclePointCollection = mapViewState.mapWindow.map.mapObjects.addCollection()

    val bitmap = context.getBitmapFromVectorDrawable(R.drawable.ic_map_pin)

    val imageProvider = ImageProvider.fromBitmap(bitmap)

    if (showAddRecyclePointBottomSheet) {
        AddRecyclePontBottomSheet(
            bottomSheetState = addRecyclePointBottomSheet,
            placeMarkDetails = placeMarkDetails!!.details,
            latitude = placeMarkDetails!!.coordinates.latitude,
            longitude = placeMarkDetails!!.coordinates.longitude,
            onAddCategoryTag = { viewModel.categoryType.value.add(it) },
            onRemoveCategoryTag = { viewModel.categoryType.value.remove(it) },
            onAddRecyclePoint = { viewModel.addRecyclePoint(it) },
            onDismissRequest = {
                mapViewState.map.mapObjects.remove(tappedPlaceMark!!)
                showAddRecyclePointBottomSheet = false
            },
            onAddingCompleted = { showAddRecyclePointBottomSheet = false }
        )
    }

    if (showRecyclePointInfoBottomSheet) {
        RecyclePointInfoBottomSheet(
            bottomSheetState = recyclePointInfoBottomSheetState,
            recyclePoint = recyclePointData!!,
            onDismissRequest = { showRecyclePointInfoBottomSheet = false }
        )
    }

    if (showPlaceMarkDetailsInfoBottomSheet) {
        PlaceMarkDetailsBottomSheet(
            bottomSheetState = placemarkDetailsBottomSheet,
            placeMarkDetails = placeMarkDetails!!,
            onNavigateToAddPlacemarkScreen =
            {
                coroutineScope.launch {
                    showPlaceMarkDetailsInfoBottomSheet = false
                    placemarkDetailsBottomSheet.hide()
                    showAddRecyclePointBottomSheet = true
                }
            },
            onDismissRequest = {
                coroutineScope.launch {
                    mapViewState.map.mapObjects.remove(tappedPlaceMark!!)
                    showPlaceMarkDetailsInfoBottomSheet = false
                }
            }
        )
    }

    val mapObjectTapListener = MapObjectTapListener { mapObject, point ->
        recyclePointData = mapObject.userData as RecyclePoint
        showRecyclePointInfoBottomSheet = true
        true
    }

    val searchListener = object : SearchListener {
        override fun onSearchResponse(response: Response) {

            val firstChild = response.collection.children.firstOrNull()?.obj
            val metadata =
                firstChild?.metadataContainer?.getItem(ToponymObjectMetadata::class.java)?.address
            val coordinates = tappedCoordinates!!
            val streetName =
                metadata?.components?.find { it.kinds.contains(Address.Component.Kind.STREET) }?.name
            val houseName =
                metadata?.components?.find { it.kinds.contains(Address.Component.Kind.HOUSE) }?.name
            val districtName =
                metadata?.components?.find { it.kinds.contains(Address.Component.Kind.DISTRICT) }?.name
            val areaName =
                metadata?.components?.find { it.kinds.contains(Address.Component.Kind.AREA) }?.name!!

            val details = if ((streetName != null) && (houseName != null))
                "$streetName $houseName"
            else districtName ?: areaName

            placeMarkDetails = PlacemarkDetails(details = details, coordinates = coordinates)

            showPlaceMarkDetailsInfoBottomSheet = true
        }

        override fun onSearchError(response: Error) {
            Toast.makeText(context, "An error occurred!", Toast.LENGTH_SHORT).show()
        }

    }

    val inputListener = object : InputListener {
        override fun onMapTap(map: Map, point: Point) {
            return
        }

        override fun onMapLongTap(map: Map, point: Point) {
            tappedPlaceMark = mapViewState.map.mapObjects.addPlacemark(point, imageProvider)
            tappedCoordinates = point
            searchManager.submit(point, 16, SearchOptions(), searchListener)
        }
    }

    AndroidView(
        factory = {
            mapViewState.apply {
                mapWindow.map.apply {
                    move(
                        CameraPosition(Point(47.227188, 39.593677), 17.0f, 0.0f, 0.0f),
                    )
                }.addInputListener(inputListener)
            }
        },
        modifier = modifier,
        update = { mapView ->

            onLoad?.invoke(mapView)

            recyclePointCollection.clear()

            Log.d("123123", "recyclePoints size recomposed: ${recyclePoints.size}")
            recyclePoints.forEach {
                recyclePointCollection.addPlacemark().apply {
                    geometry = Point(it.latitude, it.longitude)
                    userData = it
                    setIcon(imageProvider)
                }.addTapListener(mapObjectTapListener)
            }

            //TODO: change it later (adding multiple listeners)
            //mapView.map.addInputListener(inputListener)
        }
    )
}