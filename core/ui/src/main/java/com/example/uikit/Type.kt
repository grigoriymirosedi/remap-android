package com.example.core.uikit

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.R

val SFProDisplay = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.Normal),
    Font(R.font.sf_pro_display_bold, FontWeight.Bold),
    Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold)
)

val Heading1 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 38.sp
)

val Heading2 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 28.sp
)

val Subheading1 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 28.sp
)

val Subheading2 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 16.sp,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 28.sp
)

val Body1 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 20.sp
)

val Body2 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 24.sp,
)

val Metadata1 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 20.sp,
)

val Metadata2 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 10.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 16.sp,
)

val Metadata3 = TextStyle(
    fontFamily = SFProDisplay,
    fontSize = 10.sp,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 16.sp

)

val typography = RemapTypography(
    heading1 = Heading1,
    heading2 = Heading2,
    subheading1 = Subheading1,
    subheading2 = Subheading2,
    bodytext1 = Body1,
    bodytext2 = Body2,
    metadata1 = Metadata1,
    metadata2 = Metadata2,
    metadata3 = Metadata3
)

data class RemapTypography (
    val heading1: TextStyle,
    val heading2: TextStyle,
    val subheading1: TextStyle,
    val subheading2: TextStyle,
    val bodytext1: TextStyle,
    val bodytext2: TextStyle,
    val metadata1: TextStyle,
    val metadata2: TextStyle,
    val metadata3: TextStyle
)

val LocalRemapTypography = staticCompositionLocalOf {
    RemapTypography(
        heading1 = Heading1,
        heading2 = Heading2,
        subheading1 = Subheading1,
        subheading2 = Subheading2,
        bodytext1 = Body1,
        bodytext2 = Body2,
        metadata1 = Metadata1,
        metadata2 = Metadata2,
        metadata3 = Metadata3
    )
}