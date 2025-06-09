package com.example.map.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.map.ui.components.CategoryFilter
import com.example.map.ui.components.CategoryFilterChip
import com.example.map.ui.components.RecyclePointDetailsBottomSheet
import com.example.map.ui.components.RecyclePointManagerBottomSheet
import com.example.map.ui.components.YandexMapView
import com.example.map.ui.models.MapRecyclePointItem
import com.example.map.ui.models.MapUiState
import com.example.map.ui.models.State
import com.example.ui.ProgressBar
import com.example.util.INIT_LATITUDE
import com.example.util.INIT_LONGITUDE
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    latitude: Double?,
    longitude: Double?,
    modifier: Modifier = Modifier,
    onEvent: (MapEvent) -> Unit,
    uiState: State
) {
    when(uiState) {
        is State.Loading -> ProgressBar()
        is State.Error -> ErrorMessage()
        is State.Success -> MapScreenContent(
            latitude = latitude,
            longitude = longitude,
            uiState = uiState.data,
            onEvent = onEvent,
            onClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MapScreenContent(
    latitude: Double?,
    longitude: Double?,
    modifier: Modifier = Modifier,
    uiState: MapUiState,
    onEvent: (MapEvent) -> Unit,
    onClick: () -> Unit
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
            onMapLongTap = { dummyLatitude, dummyLongitude ->
                coroutineScope.launch {
                    scaffoldManagerState.bottomSheetState.partialExpand()
                }
                onEvent(MapEvent.CreateDummyRecyclePoint(dummyLatitude, dummyLongitude))
                isManagerBottomSheetVisible = true
            }
        )

        LazyRow(
            modifier = modifier.padding(horizontal = 8.dp, vertical = 12.dp).align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.spacedBy(6.dp),

        ) {
            items(CategoryFilter.entries) { category ->
                CategoryFilterChip(
                    categoryFilter = category,
                    onClick = {
                        onEvent(MapEvent.AddCategoryFilter(it))
                    }
                )
            }
        }
    }



    RecyclePointManagerBottomSheet(
        isBottomSheetVisible = isManagerBottomSheetVisible,
        scaffoldState = scaffoldManagerState,
        initialRecyclePointAddress = scaffoldManagerAddressInitialValue,
        onAddRecyclePoint = { _, _, _, _, _ -> }
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