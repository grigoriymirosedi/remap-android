package com.example.core.uikit

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

//Brand Colors
val BrandColorDefault = Color(0xffFFFFFF) // use as default app color
val BrandColorBackground = Color(0xffF7F7F7) // use for background
val BrandColorActive = Color(0xff6ABE68) // use for active button
val BrandColorActiveButtonText = Color(0xffFFFFFF) // use for brand button text
val BrandColorDisable = Color(0xffE8E8E8) // use for disable button
val BrandColorDisableButtonText = Color(0xffFFFFFF) // use for disable button text

val BrandColorTextDefault = Color(0xff25282B) // use as default text color
val BrandColorTextSecondary = Color(0xffA4A4A4) // use as secondary text color

val AccentDanger = Color(0xffE94242) // use for errors
val AccentWarning = Color(0xffFDCF41) // use for warning
val AccentSuccess = Color(0xff2CC069) // use for success

val Transparent = Color.Transparent

data class RemapColorScheme(
    val brandDefault: Color,
    val brandBackground: Color,
    val brandActive: Color,
    val brandActiveButtonText: Color,
    val brandDisable: Color,
    val brandDisableButtonText: Color,

    val brandTextDefault: Color,
    val brandTextSecondary: Color,

    val accentDanger: Color,
    val accentWarning: Color,
    val accentSuccess: Color,

    val transparent: Color
)

val LocalRemapColorScheme = staticCompositionLocalOf {
    RemapColorScheme(
        brandDefault = Color.Unspecified,
        brandBackground = Color.Unspecified,
        brandActive = Color.Unspecified,
        brandActiveButtonText = Color.Unspecified,
        brandDisable = Color.Unspecified,
        brandDisableButtonText = Color.Unspecified,
        brandTextDefault = Color.Unspecified,
        brandTextSecondary = Color.Unspecified,
        accentDanger = Color.Unspecified,
        accentWarning = Color.Unspecified,
        accentSuccess = Color.Unspecified,
        transparent = Color.Unspecified,
    )
}

val lightColorScheme = RemapColorScheme(
    brandDefault = BrandColorDefault,
    brandBackground = BrandColorBackground,
    brandActive = BrandColorActive,
    brandActiveButtonText = BrandColorActiveButtonText,
    brandDisable = BrandColorDisable,
    brandDisableButtonText = BrandColorDisableButtonText,
    brandTextDefault = BrandColorTextDefault,
    brandTextSecondary = BrandColorTextSecondary,
    accentDanger = AccentDanger,
    accentWarning = AccentWarning,
    accentSuccess = AccentSuccess,
    transparent = Transparent,
)

val darkColorScheme = RemapColorScheme(
    brandDefault = BrandColorDefault,
    brandBackground = BrandColorBackground,
    brandActive = BrandColorActive,
    brandActiveButtonText = BrandColorActiveButtonText,
    brandDisable = BrandColorDisable,
    brandDisableButtonText = BrandColorDisableButtonText,
    brandTextDefault = BrandColorTextDefault,
    brandTextSecondary = BrandColorTextSecondary,
    accentDanger = AccentDanger,
    accentWarning = AccentWarning,
    accentSuccess = AccentSuccess,
    transparent = Transparent,
)
