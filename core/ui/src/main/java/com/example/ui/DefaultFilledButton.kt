package com.example.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun DefaultFilledButton(
    text: String,
    enabled: Boolean = true,
    buttonColor: Color = RemapAppTheme.colorScheme.brandActive,
    contentColor: Color = RemapAppTheme.colorScheme.brandActiveButtonText,
    disabledButtonColor: Color = RemapAppTheme.colorScheme.brandDisable,
    disabledContentColor: Color = RemapAppTheme.colorScheme.brandDisableButtonText,
    shape: Shape = RemapAppTheme.shape.medium,
    border : BorderStroke? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = contentColor,
            disabledContainerColor = disabledButtonColor,
            disabledContentColor = disabledContentColor,
        ),
        enabled = enabled,
        border = border,
        shape = shape,
        onClick = onClick,
        )
    {
        Text(text)
    }
}

@Preview
@Composable
private fun DefaultFilledButtonPreview(
    modifier: Modifier = Modifier.width(200.dp),
    text: String = "Hello World!",
    onClick: () -> Unit = {}
) {
    RemapTheme {
        DefaultFilledButton(modifier = modifier, text = text, onClick = onClick, enabled = false)
    }
}