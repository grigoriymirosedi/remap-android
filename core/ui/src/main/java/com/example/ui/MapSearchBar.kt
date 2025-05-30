package com.example.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@Composable
fun MapSearchBar(
    modifier: Modifier = Modifier,

    shape: Shape = RemapAppTheme.shape.medium,
    onSearchClick: (String) -> Unit,
    elevation: Dp = 8.dp
) {
    var query by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier.shadow(elevation, shape),
        value = query,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = RemapAppTheme.colorScheme.brandBackground,
            unfocusedContainerColor = RemapAppTheme.colorScheme.brandBackground,
            focusedIndicatorColor = RemapAppTheme.colorScheme.transparent,
            unfocusedIndicatorColor = RemapAppTheme.colorScheme.transparent
        ),
        textStyle = RemapAppTheme.typography.subheading1.copy(fontWeight = FontWeight.Normal),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick(query)
                keyboardController?.hide()
            }
        ),
        singleLine = true,
        onValueChange = { query = it },
        placeholder = { Text("Поиск") },
        leadingIcon = { Icon(Icons.Default.Search, null) },
    )

}

@Preview
@Composable
private fun MapSearchBarPreview(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onActiveChange: (Boolean) -> Unit = {}
) {
    RemapTheme {
        MapSearchBar(
            modifier = modifier,
            onSearchClick = {}
        )
    }
}