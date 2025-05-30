package com.example.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun RoundedImageButton(
    image: ImageVector,
    enabled: Boolean = true,
    buttonColor: Color = RemapAppTheme.colorScheme.brandActive,
    imageColor: Color = RemapAppTheme.colorScheme.brandActiveButtonText,
    rotationAngle: Float = 0.0f,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    shape: Shape = CircleShape,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(buttonColor)
            .padding(2.dp)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = modifier
                .fillMaxSize()
                .offset(offsetX, offsetY)
                .rotate(rotationAngle),
            imageVector = image,
            colorFilter = ColorFilter.tint(imageColor),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun RoundedImageButtonPreview(
    modifier: Modifier = Modifier.size(25.dp),
    image: ImageVector = Icons.Outlined.Navigation,
    offsetX: Dp = 1.dp,
    rotationAngle: Float = 22.5f,
    onClick: () -> Unit = {},
) {
    RemapTheme {
        RoundedImageButton(
            modifier = modifier,
            image = image,
            offsetX = offsetX,
            rotationAngle = rotationAngle,
            onClick = onClick
        )
    }
}