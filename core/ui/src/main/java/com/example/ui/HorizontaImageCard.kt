package com.example.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun HorizontalImageCard(
    image: ImageVector,
    imageColor: Color,
    imageText: String,
    imageTextDescription: String,
    rotateAngle: Float = 0.0f,
    cardColor: Color = RemapAppTheme.colorScheme.transparent,
    contentColor: Color = RemapAppTheme.colorScheme.brandTextDefault,
    border: BorderStroke? = null,
    shape: Shape = RemapAppTheme.shape.medium,
    size: Dp = 80.dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = cardColor,
            contentColor = contentColor,
            disabledContainerColor = cardColor,
            disabledContentColor = contentColor
        ),
        border = border,
        shape = shape,
        onClick = onClick
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                modifier = modifier
                    .rotate(rotateAngle)
                    .size(size)
                    .clip(shape = shape),
                imageVector = image,
                contentDescription = null,
            )
            Column {
                Text(
                    text = imageText,
                    style = RemapAppTheme.typography.heading2,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = imageTextDescription,
                    style = RemapAppTheme.typography.bodytext1,
                    textAlign = TextAlign.Start,
                    maxLines = 3
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HorizontalImageCardPreview(
    image: ImageVector = Icons.Default.Star,
    imageColor: Color = RemapAppTheme.colorScheme.accentDanger,
    imageText: String = "Как правильно перерабатывать мусор",
    imageTextDescription: String = "Гайд о том, как перерабатывать вторсырьё",
    onClick: () -> Unit = {}
) {
    RemapTheme {
        HorizontalImageCard(
            image = image,
            imageColor = imageColor,
            imageText = imageText,
            imageTextDescription = imageTextDescription,
            onClick = onClick
        )
    }
}