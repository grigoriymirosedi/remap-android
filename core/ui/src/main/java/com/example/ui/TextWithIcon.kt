package com.example.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    text: String, icon: ImageVector,
    fontSize: TextUnit = 14.sp,
    contentColor: Color,
    textAlign: TextAlign = TextAlign.Start
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.size((fontSize.value + 6.0).dp),
            imageVector = icon, contentDescription = null,
            colorFilter = ColorFilter.tint(contentColor)
        )
        Spacer(
            modifier = Modifier.width(4.dp)
        )
        Text(
            text = text,
            fontSize = fontSize,
            color = contentColor,
            textAlign = textAlign
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithIconPreview(text: String = "ул. Мильчакова 8а", icon: ImageVector = Icons.Outlined.LocationOn) {
    TextWithIcon(text = text, icon = icon, contentColor = Color.Gray)
}