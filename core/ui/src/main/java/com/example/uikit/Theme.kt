package com.example.core.uikit

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.core.uikit.RemapTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RemapTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (isDarkMode) darkColorScheme else lightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()
        val window = (view.context as Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        DisposableEffect(systemUiController, useDarkIcons) {
            // Update all of the system bar colors to be transparent, and use
            // dark icons if we're in light theme
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
            onDispose {}
        }
    }

    CompositionLocalProvider(
        LocalRemapColorScheme provides colorScheme,
        LocalRemapTypography provides typography,
        content = content
    )
}

object RemapAppTheme {
    val colorScheme: RemapColorScheme
        @Composable
        get() = LocalRemapColorScheme.current
    val typography: RemapTypography
        @Composable
        get() = LocalRemapTypography.current
    val shape: RemapShapes
        @Composable
        get() = LocalRemapShape.current
}