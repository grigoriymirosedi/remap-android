package com.example.map.ui

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.ui.DefaultFilledButton
import com.example.core.uikit.RemapAppTheme
import com.example.map.ui.components.CategoryFilter
import com.example.map.ui.components.CategoryFilterChip
import com.example.map.ui.components.RecyclePointDetailsBottomSheet
import com.example.map.ui.components.RecyclePointManagerBottomSheet
import com.example.map.ui.components.RecyclePointMapView
import com.example.map.ui.components.YandexMapView
import com.example.map.ui.models.MapRecyclePointItem
import com.example.map.ui.models.MapUiState
import com.example.map.ui.models.State
import com.example.ui.IconButton
import com.example.ui.ProgressBar
import com.example.util.INIT_LATITUDE
import com.example.util.INIT_LONGITUDE
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    latitude: Double?,
    longitude: Double?,
    modifier: Modifier = Modifier,
    onEvent: (MapEvent) -> Unit,
    uiState: State,
) {

    val locationPermissions = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(Unit) {
        if (!locationPermissions.allPermissionsGranted) {
            locationPermissions.launchMultiplePermissionRequest()
        }
    }

    val hasLocationPermission by remember {
        derivedStateOf { locationPermissions.allPermissionsGranted }
    }

    when (uiState) {
        is State.Loading -> ProgressBar()
        is State.Error -> ErrorMessage()
        is State.Success -> MapScreenContent(
            latitude = latitude,
            longitude = longitude,
            uiState = uiState.data,
            onEvent = onEvent,
            onClick = {},
            hasLocationPermission = hasLocationPermission,
            locationPermissions = locationPermissions
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
private fun MapScreenContent(
    latitude: Double?,
    longitude: Double?,
    modifier: Modifier = Modifier,
    uiState: MapUiState,
    onEvent: (MapEvent) -> Unit,
    onClick: () -> Unit,
    hasLocationPermission: Boolean,
    locationPermissions: MultiplePermissionsState
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberModalBottomSheetState()
    )
    val scaffoldManagerState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberModalBottomSheetState()
    )
    var selectedPoint by remember { mutableStateOf<MapRecyclePointItem?>(null) }
    val coroutineScope = rememberCoroutineScope()

    var scaffoldManagerAddressInitialValue by remember { mutableStateOf("") }

    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var isManagerBottomSheetVisible by remember { mutableStateOf(false) }

    val mapViewRef = remember { mutableStateOf<RecyclePointMapView?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        YandexMapView(
            latitude = latitude ?: INIT_LATITUDE,
            longitude = longitude ?: INIT_LONGITUDE,
            modifier = modifier.fillMaxSize(),
            recyclePoints = uiState.recyclePoints,
            categoryFilters = uiState.categoryFilters,
            onRecyclePointClick = { recyclePoint ->
                coroutineScope.launch {
                    scaffoldState.bottomSheetState.partialExpand()
                }
                selectedPoint = recyclePoint
                isBottomSheetVisible = true
            },
            onMapClick = {
                coroutineScope.launch {
                    onEvent(MapEvent.DeleteDummyRecyclePoint)
                    scaffoldState.bottomSheetState.hide()
                    isBottomSheetVisible = false
                    isManagerBottomSheetVisible = false
                    scaffoldManagerState.bottomSheetState.hide()
                    selectedPoint = null
                }
            },
            onSearchResult = {
                scaffoldManagerAddressInitialValue = it
            },
            mapViewReference = mapViewRef,
            hasLocationPermission = hasLocationPermission,
            onMapLongTap = { dummyLatitude, dummyLongitude ->
                coroutineScope.launch {
                    scaffoldManagerState.bottomSheetState.partialExpand()
                }
                onEvent(MapEvent.CreateDummyRecyclePoint(dummyLatitude, dummyLongitude))
                isManagerBottomSheetVisible = true
            }
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 12.dp, vertical = 60.dp)
                .size(50.dp),
            contentColor = RemapAppTheme.colorScheme.brandDefault,
            containerColor = RemapAppTheme.colorScheme.brandActive.copy(),
            icon = Icons.Filled.NearMe,
            shape = RoundedCornerShape(16.dp),
            onClick = {
                if(!hasLocationPermission) {
                    locationPermissions.launchMultiplePermissionRequest()
                } else {
                    mapViewRef.value?.moveToUserLocation()
                }
            }
        )
        LazyRow(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 12.dp)
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.spacedBy(6.dp),

            ) {
            items(CategoryFilter.entries) { category ->
                CategoryFilterChip(
                    categoryFilter = category,
                    onClick = { categoryName, _ ->
                        onEvent(MapEvent.AddCategoryFilter(categoryName))
                    }
                )
            }
        }

    }



    RecyclePointManagerBottomSheet(
        isBottomSheetVisible = isManagerBottomSheetVisible,
        scaffoldState = scaffoldManagerState,
        initialRecyclePointAddress = scaffoldManagerAddressInitialValue,
        onAddRecyclePoint = { name, description, address, locationHint, phoneNumber, workingHours, acceptedItems ->
            onEvent(
                MapEvent.AddRecyclePoint(
                    name = name,
                    description = description,
                    address = address,
                    locationHint = locationHint,
                    phoneNumber = phoneNumber,
                    workingHours = workingHours,
                    acceptedItems = acceptedItems
                )
            )
            coroutineScope.launch {
                scaffoldManagerState.bottomSheetState.hide()
            }
        },
    )

    RecyclePointDetailsBottomSheet(
        recyclePointItem = selectedPoint,
        isBottomSheetVisible = isBottomSheetVisible,
        scaffoldState = scaffoldState
    )
}

@Composable
fun ErrorMessage() {
}