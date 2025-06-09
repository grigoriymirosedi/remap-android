package com.example.map.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme
import com.example.ui.R
import com.example.map.ui.models.MapRecyclePointItem
import com.example.map.ui.models.toStringCategory
import com.example.ui.RecycleAcceptedItemChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecyclePointDetailsBottomSheet(
    modifier: Modifier = Modifier,
    recyclePointItem: MapRecyclePointItem?,
    scaffoldState: BottomSheetScaffoldState,
    isBottomSheetVisible: Boolean,
    recyclePointNameTextStyle: TextStyle = RemapAppTheme.typography.heading2,
    recyclePointAddressTextStyle: TextStyle = RemapAppTheme.typography.bodytext2.copy(fontWeight = FontWeight.Normal, color = RemapAppTheme.colorScheme.brandTextSecondary),
    scaffoldPeekHeight: Dp = 100.dp,
    sheetShape: Shape = RemapAppTheme.shape.medium
) {
    if(isBottomSheetVisible && recyclePointItem != null) {
        BottomSheetScaffold(
            sheetContainerColor = RemapAppTheme.colorScheme.brandDefault,
            scaffoldState = scaffoldState,
            sheetPeekHeight = scaffoldPeekHeight,
            sheetContent = {
                Column(
                    modifier = modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    println(recyclePointItem.toString())
                    recyclePointItem.imageUrl?.let {
                        AsyncImage(
                            modifier = modifier.height(200.dp)
                                .clip(shape = sheetShape),
                            model = it,
                            placeholder = painterResource(id = R.drawable.placeholder),
                            contentScale = ContentScale.Crop,
                            error = painterResource(id = R.drawable.placeholder),
                            contentDescription = null
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        recyclePointItem.acceptedItems.forEach { acceptedItem ->
                            RecycleAcceptedItemChip(item = acceptedItem)
                        }
                    }
                    Text(text = recyclePointItem.name, style = recyclePointNameTextStyle)
                    Text(text = recyclePointItem.address, style = recyclePointAddressTextStyle)
                    recyclePointItem.locationHint?.let { hintText ->
                        Text(text = hintText, style = recyclePointAddressTextStyle)
                    }
                    Text(text = recyclePointItem.description, style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.Normal))
                    Text(text = "Часы работы: ${recyclePointItem.workingHours}", style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.Normal))
                    recyclePointItem.phoneNumber?.let { phoneNumber ->
                        Text(text = "Контакты: $phoneNumber", style = RemapAppTheme.typography.subheading2.copy(fontWeight = FontWeight.Normal))
                    }
                }
            }
        ) {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RecyclePointDetailsBottomSheetPreview(
    modifier: Modifier = Modifier,
    recyclePointItem: MapRecyclePointItem = MapRecyclePointItem(
        name = "Мехматовская мусорка",
        address = "ул. Мильчакова 8а",
        latitude = 123123.0,
        longitude = 123123.0,
        acceptedItems = emptyList(),
        workingHours = "Круглосуточно",
        phoneNumber = null,
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq42Q5fT68X6BzxLKttK9M7exYW6bEi1BX6Q&s",
        locationHint = "Мусорка находится за мехматом со стороны курилки",
        description = "Круглосуточная мусорка по приёму отходов",
        moderationStatus = 0,
        isDummy = false,
    )
) {
    RemapTheme {
        RecyclePointDetailsBottomSheet(
            recyclePointItem = recyclePointItem,
            isBottomSheetVisible = true,
            scaffoldState = rememberBottomSheetScaffoldState(),
        )
    }
}