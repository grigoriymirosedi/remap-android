package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    color: Color = RemapAppTheme.colorScheme.brandActive
) {
    RemapTheme {
        Box(
            modifier = modifier,
            contentAlignment = alignment
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = color
            )
        }
    }
}