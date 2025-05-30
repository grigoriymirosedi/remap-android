package com.example.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.uikit.RemapAppTheme
import com.example.ui.R

@Composable
fun EventCard(
    imageURL: String,
    title: String,
    date: String,
    time: String,
    location: String,
    onClick: () -> Unit,
    background: Color = RemapAppTheme.colorScheme.brandBackground,
    shape: Shape = RemapAppTheme.shape.medium,
    width: Dp = 300.dp,
    imageHeight: Dp = 160.dp,
    zoom: Float = 15.5f,
    @DrawableRes placeholderImage: Int = R.drawable.placeholder,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.width(width = width),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                modifier = modifier.height(imageHeight),
                model = imageURL,
                placeholder = painterResource(id = placeholderImage),
                error = painterResource(id = placeholderImage),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = modifier
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = RemapAppTheme.typography.subheading2
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = date,
                        textAlign = TextAlign.End,
                        style = RemapAppTheme.typography.bodytext2,
                        color = Color(0xff808080)//Color(0xffA9A9A9)
                    )
                    Text(
                        text = time,
                        textAlign = TextAlign.End,
                        style = RemapAppTheme.typography.bodytext2,
                        color = Color(0xff808080)//Color(0xffA9A9A9)
                    )
                }
                Text(
                    text = location,
                    textAlign = TextAlign.End,
                    style = RemapAppTheme.typography.bodytext2,
                    color = Color(0xff808080)//Color(0xffA9A9A9)
                )
            }
        }

    }
}

@Preview
@Composable
private fun EventCardPreview(
    imageURL: String = "https://www.donland.ru/upload/uf/74e/Botanich-sad_sayt1.jpeg",
    title: String = "Субботник в ботаническом саду ЮФУ",
    date: String = "20 августа",
    time: String = "12:00-14:00",
    location: String = "пер. Ботанический спуск 7",
) {
    EventCard(
        imageURL = imageURL,
        title = title,
        date = date,
        time = time,
        location = location,
        onClick = {}
    )
}