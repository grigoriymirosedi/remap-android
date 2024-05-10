package com.example.remap.ui.screens.map

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remap.ui.screens.calendar.MapViewModel
import com.example.remap.ui.screens.map.components.CategoryType
import com.example.remap.ui.utils.CategoryFilterChip

@Composable
fun MapScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: MapViewModel = hiltViewModel(),
) {

    val (checkedState, onStateChange) = remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        MapView(viewModel = viewModel)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .toggleable(
                        value = checkedState,
                        onValueChange = { onStateChange(!checkedState) },
                        role = Role.Checkbox
                    )
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            )
            {
                CategoryType.values().forEach { category ->
                    CategoryFilterChip(
                        categoryType = category,
                        onClick = {
                            viewModel.filterCategories.add(category.sourceName)
                            viewModel.updateRecyclePoints()
                        },
                        onDissmiss = {
                            viewModel.filterCategories.remove(category.sourceName)
                            viewModel.updateRecyclePoints()
                        },
                        colors = FilterChipDefaults.filterChipColors(containerColor = Color(0xfff7faf2))
                    )
                }
            }
        }
    }
}