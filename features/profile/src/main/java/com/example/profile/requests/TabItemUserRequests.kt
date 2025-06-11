package com.example.profile.requests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme
import java.time.LocalDate
import java.util.UUID

@Composable
internal fun TabItemUserRequests(
    modifier: Modifier = Modifier,
    shape: Shape = RemapAppTheme.shape.medium,
    requests: List<RequestItem>,
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(requests) { requestItem ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = shape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xffE8F5E9)
                )
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(2f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Заявка #" + requestItem.requestNumber,
                                style = RemapAppTheme.typography.subheading2
                            )
                            Text(
                                text = requestItem.requestTitle,
                                style = RemapAppTheme.typography.subheading1.copy(fontWeight = FontWeight.SemiBold),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = requestItem.address,
                                style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.Normal),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Text(
                            text = "Категория: " + requestItem.category,
                            style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.Normal)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        RequestStatusCard(
                            requestStatus = requestItem.requestStatus
                        )

                        Text(
                            text = "Дата: " + requestItem.requestDate,
                            style = RemapAppTheme.typography.metadata1
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabItemUserRequestsPreview() {
    val requests = listOf(
        RequestItem(
            requestNumber = UUID.randomUUID().toString().take(10),
            requestStatus = 0,
            category = "Магазин",
            requestTitle = "Пункт приёма пластиковых бутылок",
            requestDate = "11.06.2025",
            address = "ул. Мильчакова 8а"
        ),

        RequestItem(
            requestNumber = UUID.randomUUID().toString().take(10),
            requestStatus = 1,
            category = "Пункт переработки",
            requestTitle = "Подари дереву жизнь",
            requestDate = "11.06.2025",
            address = "ул. Мильчакова 8а"
        ),

        RequestItem(
            requestNumber = UUID.randomUUID().toString().take(10),
            requestStatus = -1,
            category = "Магазин",
            requestTitle = "Подари дереву жизнь",
            requestDate = "11.06.2025",
            address = "ул. Мильчакова 8а"
        )
    )
    RemapTheme {
        TabItemUserRequests(
            requests = requests
        )
    }
}