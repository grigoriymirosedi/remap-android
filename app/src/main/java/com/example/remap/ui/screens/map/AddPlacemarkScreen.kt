package com.example.remap.ui.screens.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun AddPlacemarkScreen(placeMarkDetails: String, latitude: Double, longitude: Double) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(modifier = Modifier.fillMaxWidth(), text = placeMarkDetails)
            Text(modifier = Modifier.fillMaxWidth(), text = latitude.toString())
            Text(modifier = Modifier.fillMaxWidth(), text = longitude.toString())
        }
    }
}