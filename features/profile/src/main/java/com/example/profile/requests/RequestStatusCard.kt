package com.example.profile.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun RequestStatusCard(
    modifier: Modifier = Modifier,
    requestStatus: Int,
    shape: Shape = RemapAppTheme.shape.medium,
) {
    val statusText = when (requestStatus) {
        APPROVED -> { // APPROVED
            "Принято"
        }
        PENDING -> { // PENDING
            "Рассматривается"
        }
        else -> "Отклонено" // REJECTED
    }

    val containerColor = when (requestStatus) {
        APPROVED -> { // APPROVED
            RemapAppTheme.colorScheme.accentSuccess
        }
        PENDING -> { // PENDING
            RemapAppTheme.colorScheme.accentWarning
        }
        else -> RemapAppTheme.colorScheme.accentDanger // REJECTED
    }

    val contentColor = when (requestStatus) {
        APPROVED -> { // APPROVED
            RemapAppTheme.colorScheme.accentSuccess
        }
        PENDING -> { // PENDING
            Color(0xfff7bd02)
        }
        else -> RemapAppTheme.colorScheme.accentDanger // REJECTED
    }


    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor.copy(alpha = 0.2f),
            contentColor = contentColor
        ),
        shape = shape,
    ) {
        Text(
            modifier = Modifier.padding(6.dp),
            text = statusText,
            style = RemapAppTheme.typography.metadata1.copy(color = contentColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RequestStatusCardPreview(
    status: Int = PENDING,
) {
    RemapTheme {
        RequestStatusCard(
            requestStatus = status
        )
    }
}