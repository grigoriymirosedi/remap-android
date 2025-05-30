package com.example.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.uikit.RemapAppTheme
import com.example.ui.R

@Composable
fun EcoCommunityCard(
    communityImageURL: String,
    title: String,
    size: Dp = 140.dp,
    shape: Shape = RemapAppTheme.shape.medium,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = modifier
                .size(size)
                .clip(shape),
            model = communityImageURL,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = RemapAppTheme.typography.subheading2
        )
    }
}

@Preview
@Composable
private fun EcoCommunityCardPreview(
    communityImageURL: String = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJOM_yNOJbX7gBTuvJuxu99wthpNXth8CP3g&s",
    title: String = "Экомост",
    description: String = "Центр занимается поддержкой и реализацией различных экологических проектов и инициатив в регионе",
) {
    EcoCommunityCard(
        communityImageURL = communityImageURL,
        title = title,
        onClick = {}
    )
}