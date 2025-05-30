package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun RecycleEventItemCard(
    modifier: Modifier = Modifier.height(250.dp),
    imageUrl: String,
    eventTitle: String,
    eventDescription: String,
    eventDateDay: String,
    eventDateMonth: String,
    onClick: () -> Unit,
    shape: Shape = RemapAppTheme.shape.medium
) {
    Box(modifier = modifier) {
        Card(
            modifier = modifier,
            shape = shape,
            onClick = onClick
        ) {
            AsyncImage(
                modifier = modifier,
                model = imageUrl,
                placeholder = painterResource(R.drawable.recycle_event_placeholder),
                error = painterResource(R.drawable.event_image_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            RecycleEventItemDate(
                day = eventDateDay,
                month = eventDateMonth
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp).align(Alignment.BottomStart),
        ) {
            Text(text = eventTitle, style = RemapAppTheme.typography.heading2.copy(color = RemapAppTheme.colorScheme.brandDefault))
            Text(text = eventDescription,
                style = RemapAppTheme.typography.bodytext2.copy(color = Color(0xfff5f5f5), fontWeight = FontWeight.SemiBold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Preview
@Composable
private fun RecycleEventItemPreview(
    eventTitle: String = "Субботник в ботаническом саду ЮФУ",
    eventDescription: String = "Тестовый субботник у ботанического сада ЮФУ",
    eventDateDay: String = "11",
    eventDateMonth: String = "Апреля",
    imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/a/a8/%D0%A1%D0%B0%D0%B4_%283%29.jpg"
) {
    RemapTheme {
        RecycleEventItemCard(
            imageUrl = imageUrl,
            eventTitle = eventTitle,
            eventDescription = eventDescription,
            eventDateDay = eventDateDay,
            eventDateMonth = eventDateMonth,
            onClick = {}
        )
    }
}