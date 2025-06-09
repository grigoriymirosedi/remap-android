package com.example.map.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun CategoryFilterChip(
    categoryFilter: CategoryFilter,
    onClick: (String) -> Unit,
) {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        colors = FilterChipDefaults.filterChipColors(
            containerColor = RemapAppTheme.colorScheme.brandActive.copy(alpha = 0.45f),
            selectedContainerColor = RemapAppTheme.colorScheme.brandActive
        ),
        onClick = {
            selected = !selected
            onClick(categoryFilter.displayName)
        },
        label = {
            Text(
                text = categoryFilter.displayName,
                fontSize = 18.sp
            )
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            {
                Icon(
                    imageVector = categoryFilter.icon,
                    tint = categoryFilter.categoryColor,
                    contentDescription = null,
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryFilterPreview(
    categoryFilter: CategoryFilter = CategoryFilter.Paper,
) {
    RemapTheme {
        CategoryFilterChip(
            categoryFilter = categoryFilter,
            onClick = {}
        )
    }
}
