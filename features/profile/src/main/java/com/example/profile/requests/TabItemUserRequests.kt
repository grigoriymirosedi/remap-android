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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme
import java.util.UUID

@Composable
internal fun TabItemUserRequests(
    modifier: Modifier = Modifier,
    shape: Shape = RemapAppTheme.shape.medium,
    requests: List<RequestItem>
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(requests) { requestItem ->
            Card(
                modifier = modifier.fillMaxWidth().height(100.dp),
                shape = shape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xffE8F5E9)
                )
            ) {
                Row(
                    modifier = modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            text = "Заявка #" + requestItem.requestNumber,
                            style = RemapAppTheme.typography.subheading1
                        )
                        Text(
                            text = requestItem.requestTitle,
                            style = RemapAppTheme.typography.subheading1.copy(fontWeight = FontWeight.Normal)
                        )
                    }
                    RequestStatusCard(
                        requestStatus = requestItem.requestStatus
                    )
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
            requestTitle = "Подари дереву жизнь",
            requestDate = "22.04.2025"
        ),

        RequestItem(
            requestNumber = UUID.randomUUID().toString().take(10),
            requestStatus = 1,
            requestTitle = "Подари дереву жизнь",
            requestDate = "22.04.2025"
        ),

        RequestItem(
            requestNumber = UUID.randomUUID().toString().take(10),
            requestStatus = -1,
            requestTitle = "Подари дереву жизнь",
            requestDate = "22.04.2025"
        )
    )
    RemapTheme {
        TabItemUserRequests(
            requests = requests
        )
    }
}