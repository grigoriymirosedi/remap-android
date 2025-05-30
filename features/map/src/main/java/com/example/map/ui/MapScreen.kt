package com.example.map.ui

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.map.ui.components.RecyclePointDetailsBottomSheet
import com.example.map.ui.components.RecyclePointManagerBottomSheet
import com.example.map.ui.components.YandexMapView
import com.example.map.ui.models.MapRecyclePointItem
import com.example.map.ui.models.MapUiState
import com.example.map.ui.models.State
import com.example.ui.IconButton
import com.example.ui.MapSearchBar
import com.example.ui.ProgressBar
import com.example.util.INIT_LATITUDE
import com.example.util.INIT_LONGITUDE
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch

@Composable
fun MapScreen(
    latitude: Double?,
    longitude: Double?,
    modifier: Modifier = Modifier,
    uiState: State
) {
    when(uiState) {
        is State.Loading -> ProgressBar()
        is State.Error -> ErrorMessage()
        is State.Success -> MapScreenContent(
            latitude = latitude,
            longitude = longitude,
            uiState = uiState.data,
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
    onClick: () -> Unit
) {

    val context = LocalContext.current

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberModalBottomSheetState()
    )
    val scaffoldManagerState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberModalBottomSheetState()
    )
    var selectedPoint by remember { mutableStateOf<MapRecyclePointItem?>(null) }
    val coroutineScope = rememberCoroutineScope()

    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var isManagerBottomSheetVisible by remember { mutableStateOf(false) }

    YandexMapView(
        latitude = latitude ?: INIT_LATITUDE,
        longitude = longitude ?: INIT_LONGITUDE,
        modifier = Modifier.fillMaxSize(),
        recyclePoints = uiState.recyclePoints,
        onRecyclePointClick = { recyclePoint ->
            coroutineScope.launch {
                scaffoldState.bottomSheetState.partialExpand()
            }
            selectedPoint = recyclePoint
            isBottomSheetVisible = true
        },
        onMapClick = {
            coroutineScope.launch {
                scaffoldState.bottomSheetState.hide()
                isBottomSheetVisible = false
                isManagerBottomSheetVisible = false
                scaffoldManagerState.bottomSheetState.hide()
                selectedPoint = null
            }
        },
        onMapLongTap = {
            coroutineScope.launch {
                scaffoldManagerState.bottomSheetState.partialExpand()
            }
            isManagerBottomSheetVisible = true
        }
    )
    Row(
        modifier = modifier.fillMaxWidth().height(IntrinsicSize.Min)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        MapSearchBar(modifier = modifier.weight(5f), onSearchClick = {})
        IconButton(
            modifier = modifier.weight(1f).fillMaxHeight(),
            icon = Icons.Outlined.Tune,
            onClick = onClick
        )
    }

    RecyclePointManagerBottomSheet(
        isBottomSheetVisible = isManagerBottomSheetVisible,
        scaffoldState = scaffoldManagerState,
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