package com.example.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun CollectedRecyclableItemCard(
    image: ImageVector,
    imageColor: Color,
    headerText: String,
    headerTextStyle: TextStyle = RemapAppTheme.typography.metadata1,
    recycledCountText: String,
    recycledCountTextStyle: TextStyle = RemapAppTheme.typography.heading2,
    rotateAngle: Float = -45f,
    cardColor: Color = RemapAppTheme.colorScheme.brandDisable,
    contentColor: Color = RemapAppTheme.colorScheme.brandTextDefault,
    border: BorderStroke? = null,
    offsetX: Dp = 15.dp,
    offsetY: Dp = 25.dp,
    shape: Shape = RemapAppTheme.shape.medium,
    size: Dp = 120.dp,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.size(size),
        colors = CardColors(
            containerColor = cardColor,
            contentColor = contentColor,
            disabledContentColor = cardColor,
            disabledContainerColor = contentColor
        ),
        border = border,
        shape = shape,
    ) {
        Column(
            modifier = modifier.padding(start = 6.dp, top = 6.dp)
        ) {
            Text(
                text = headerText,
                style = headerTextStyle
            )
            Row(
                modifier = modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = recycledCountText,
                        style = recycledCountTextStyle
                    )
                    Text(
                        text = "Единиц",
                        style = RemapAppTheme.typography.bodytext2
                    )
                }
                Icon(
                    imageVector = image,
                    modifier = modifier
                        .fillMaxSize()
                        .offset(offsetX, offsetY)
                        .rotate(rotateAngle),
                    tint = imageColor,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun CollectedRecyclableItemCardPreview(
    image: ImageVector = Icons.Default.WaterDrop,
    imageColor: Color = Color(0xff6ABE68),
    headerText: String = "Сдано пластика",
    recycledCountText: String = "13",
    onClick: () -> Unit = {},
) {
    RemapTheme {
        CollectedRecyclableItemCard(
            image = image,
            imageColor = imageColor,
            headerText = headerText,
            recycledCountText = recycledCountText,
        )
    }
}

