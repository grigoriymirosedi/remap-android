package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    containerColor: Color = RemapAppTheme.colorScheme.brandBackground,
    contentColor: Color = RemapAppTheme.colorScheme.brandTextDefault,
    shape: Shape = RemapAppTheme.shape.small,
    elevation: Dp = 8.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .shadow(elevation, shape)
            .background(containerColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            tint = contentColor,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun IconButtonPreview(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.Tune,
    onClick: () -> Unit = {},
) {
    RemapTheme {
        IconButton(modifier = Modifier.size(30.dp), icon = icon, onClick = onClick)
    }
}