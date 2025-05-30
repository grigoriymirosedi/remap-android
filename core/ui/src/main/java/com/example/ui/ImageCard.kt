package com.example.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material.icons.outlined.Forest
import androidx.compose.material.icons.outlined.Nature
import androidx.compose.material.icons.outlined.NaturePeople
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun ImageCard(
    image: ImageVector,
    rotateAngle: Float = 0.0f,
    cardColor: Color = RemapAppTheme.colorScheme.brandActive,
    contentColor: Color = RemapAppTheme.colorScheme.brandActiveButtonText,
    disabledCardColor: Color = RemapAppTheme.colorScheme.brandDisable,
    disabledContentColor: Color = RemapAppTheme.colorScheme.brandDisableButtonText,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    shape: Shape = RemapAppTheme.shape.medium,
    size: Dp = 200.dp,
    imageText: String? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = modifier.size(size),
            enabled = enabled,
            colors = CardColors(
                containerColor = cardColor,
                contentColor = contentColor,
                disabledContainerColor = disabledCardColor,
                disabledContentColor = disabledContentColor
            ),
            shape = shape,
            border = border,
            onClick = onClick
        ) {
            Image(
                modifier = modifier
                    .fillMaxSize()
                    .rotate(rotateAngle),
                imageVector = image,
                contentDescription = null
            )
        }
        if(imageText != null) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = imageText ?: "",
                maxLines = 2,
                textAlign = TextAlign.Center,
                style = RemapAppTheme.typography.bodytext1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageCardPreview(
    modifier: Modifier = Modifier,
    image: ImageVector = Icons.Outlined.Forest,
    imageText: String = "Hello hel123 123 123 123 123 213  World",
    onClick: () -> Unit = {},
) {
    RemapTheme {
        ImageCard(image = image, imageText = imageText, onClick = onClick)
    }
}