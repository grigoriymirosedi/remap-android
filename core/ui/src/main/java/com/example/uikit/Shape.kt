package com.example.core.uikit

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val none = RoundedCornerShape(0.dp)
val extraSmall = RoundedCornerShape(2.dp)
val small = RoundedCornerShape(8.dp)
val medium = RoundedCornerShape(16.dp)
val large = RoundedCornerShape(24.dp)
val extraLarge = RoundedCornerShape(32.dp)

data class RemapShapes (
    val none: Shape,
    val extraSmall: Shape,
    val small: Shape,
    val medium: Shape,
    val large: Shape,
    val extraLarge: Shape,
)

val LocalRemapShape = staticCompositionLocalOf {
    RemapShapes(
        none = none,
        extraSmall = extraSmall,
        small = small,
        medium = medium,
        large = large,
        extraLarge = extraLarge
    )
}