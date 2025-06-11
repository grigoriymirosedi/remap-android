package com.example.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun RecyclePointsNearbyCard(
    title: String,
    address: String,
    latitude: Double,
    longitude: Double,
    width: Dp = 250.dp,
    mapHeight: Dp = 160.dp,
    zoom: Float = 15.5f,
    shape: Shape = RemapAppTheme.shape.medium,
    background: Color = RemapAppTheme.colorScheme.brandBackground,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.width(width = width),
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick() },
    ) {
        Column(
            modifier = modifier
        ) {
            StaticYandexMapView(
                modifier = modifier
                    .height(mapHeight)
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    ),
                latitude = latitude,
                longitude = longitude,
                zoom = zoom,
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = RemapAppTheme.typography.subheading2
                )
                Text(
                    text = address,
                    style = RemapAppTheme.typography.bodytext2,
                    color = Color(0xff808080) //Color(0xff808080)
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecyclePointsNearbyCardPreview(
    title: String = "Мехматовская мусорка",
    address: String = "ул. Мильчакова 8а",
    distance: Double = 400.3,
    latitude: Double = 47.216686,
    longitude: Double = 39.628649,
    onClick: () -> Unit = {},
) {
    RemapTheme {
        RecyclePointsNearbyCard(
            title = title,
            address = address,
            latitude = latitude,
            longitude = longitude,
            onClick = onClick
        )
    }
}