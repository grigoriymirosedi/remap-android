package com.example.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.core.models.CategoryType

@Composable
fun CategoryFilterChip(
    categoryType: CategoryType,
    modifier: Modifier = Modifier,
    onClick: (CategoryType) -> Unit,
    colors: SelectableChipColors = FilterChipDefaults.filterChipColors(),
    onDismiss: (CategoryType) -> Unit,
    iconColor: Color = Color.White,
) {

    var isSelected by remember { mutableStateOf(false) }

    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = {
            isSelected = !isSelected
            if (isSelected) {
                onClick(categoryType)
            } else
                onDismiss(categoryType)
        },
        label = { Text(text = categoryType.text) },
        leadingIcon = {
            Icon(
                imageVector = if (isSelected) Icons.Filled.Done else categoryType.sourceImage,
                contentDescription = null,
            )
        },
        colors = colors
    )
}
