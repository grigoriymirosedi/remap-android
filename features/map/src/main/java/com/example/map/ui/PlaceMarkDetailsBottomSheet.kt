package com.example.map.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.models.PlacemarkDetails
import com.example.core.util.toStringLatLngFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceMarkDetailsBottomSheet(
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState,
    placeMarkDetails: PlacemarkDetails,
    onNavigateToAddPlacemarkScreen: () -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            com.example.core.ui.TitleText(text = "Точка на карте")
            Spacer(modifier = Modifier.size(4.dp))
            com.example.core.ui.TextWithIcon(
                modifier = Modifier.fillMaxWidth(),
                text = placeMarkDetails.coordinates.toStringLatLngFormat(),
                icon = Icons.Filled.ContentCopy,
                fontSize = 14.sp,
                contentColor = Color.Gray
            )
            Spacer(modifier = Modifier.size(8.dp))
            com.example.core.ui.TextWithIcon(
                text = placeMarkDetails.details,
                icon = Icons.Filled.Place,
                fontSize = 18.sp,
                contentColor = Color.Gray
            )
            Spacer(modifier = Modifier.size(4.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                Button(onClick = onNavigateToAddPlacemarkScreen) {
                    com.example.core.ui.TextWithIcon(
                        text = "Добавить объект",
                        icon = Icons.Filled.AddLocationAlt,
                        contentColor = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}