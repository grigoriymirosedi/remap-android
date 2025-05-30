package com.example.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme
import com.example.ui.R

@Composable
fun MascotInfoCard(
    infoText: String,
    infoTextStyle: TextStyle = RemapAppTheme.typography.bodytext2,
    @DrawableRes mascotImageId: Int = R.drawable.tip_mascot,
    shape: Shape = RemapAppTheme.shape.medium,
    border: BorderStroke? = null,
    backgroundColor: Color = Color(0xff90AFD4),
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        border = border,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = modifier
                .padding(start = 12.dp, end = 24.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                modifier = modifier.size(size),
                painter = painterResource(id = mascotImageId),
                contentDescription = null
            )
            RectangleTextCallout(
                modifier = modifier.align(Alignment.Top),
                text = infoText,
                textStyle = infoTextStyle
            )
        }
    }
}

@Preview
@Composable
private fun MascotInfoCardPreview(
    infoText: String = "Здарова Димон, я зелёная клякса, маскот Ремапа, меня создал (спиздил) CEO, CFO CIO, COO, CTO, CSO, Head of Mobile Development at @remaptech",
) {
    RemapTheme {
        MascotInfoCard(
            infoText = infoText,
        )
    }
}