package com.example.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.util.INIT_LATITUDE
import com.example.util.INIT_LONGITUDE
import com.yandex.mapkit.geometry.Point

@Composable
fun MapScreenRoute(
    latitude: Double?,
    longitude: Double?,
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(MapEvent.LoadRecyclePoints)
    }
    MapScreen(
        latitude = latitude,
        longitude = longitude,
        modifier = modifier,
        onEvent = { viewModel.handleEvent(it) },
        uiState = uiState
    )
}