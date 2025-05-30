package com.example.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun PointsCard(
    pointsAmount: String,
    pointsAmountTextStyle: TextStyle = RemapAppTheme.typography.subheading1,
    backgroundColor: Color = RemapAppTheme.colorScheme.brandDefault,
    image: ImageVector = Icons.Default.Brightness5,
    imageColor: Color = Color(0xff6ABE68),
    shape: Shape = RemapAppTheme.shape.medium,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = RemapAppTheme.colorScheme.brandDefault
        ),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 4.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                imageVector = image,
                tint = imageColor,
                contentDescription = null
            )
            Text(
                text = pointsAmount,
                style = pointsAmountTextStyle
            )
        }
    }
}

@Preview
@Composable
private fun PointsCardPreview(
    pointsAmount: String = "250",
    onClick: () -> Unit = {}
) {
    RemapTheme {
        PointsCard(
            pointsAmount = pointsAmount,
        )
    }
}