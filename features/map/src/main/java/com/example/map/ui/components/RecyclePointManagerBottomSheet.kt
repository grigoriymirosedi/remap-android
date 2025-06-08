package com.example.map.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Title
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.ui.DefaultFilledButton
import com.example.core.ui.TitleText
import com.example.core.uikit.RemapAppTheme
import com.example.core.uikit.RemapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecyclePointManagerBottomSheet(
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState,
    isBottomSheetVisible: Boolean,
    initialRecyclePointAddress: String,
    scaffoldPeekHeight: Dp = 200.dp,
    sheetShape: Shape = RemapAppTheme.shape.medium,
    onAddRecyclePoint: (String, String, String, String, String) -> Unit,
) {
    var recyclePointTitle by remember { mutableStateOf("") }
    var recyclePointDescription by remember { mutableStateOf("") }
    var recyclePointAddress by remember { mutableStateOf(initialRecyclePointAddress) }
    var recyclePointLocationHint by remember { mutableStateOf("") }
    var recyclePointWorkingHours by remember { mutableStateOf("") }
    var recyclePointContacts by remember { mutableStateOf("") }

    LaunchedEffect(initialRecyclePointAddress) {
        recyclePointAddress = initialRecyclePointAddress
    }

    if (isBottomSheetVisible) {
        BottomSheetScaffold(
            sheetContainerColor = RemapAppTheme.colorScheme.brandDefault,
            scaffoldState = scaffoldState,
            sheetPeekHeight = scaffoldPeekHeight,
            sheetShape = sheetShape,
            sheetContent = {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 6.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TitleText(text = "Новая точка переработки")
                    Spacer(modifier = Modifier.size(8.dp))
                    OutlinedTextField(
                        value = recyclePointTitle,
                        onValueChange = {
                            recyclePointTitle = it
                        },
                        label = { Text(text = "Название пункта переработки") },
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Title,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = recyclePointDescription,
                        onValueChange = {
                            recyclePointDescription = it
                        },
                        label = { Text(text = "Описание") },
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Description,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = recyclePointAddress,
                        onValueChange = {
                            recyclePointAddress = it
                        },
                        label = { Text(text = "Адрес") },
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.LocationCity,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = recyclePointLocationHint,
                        onValueChange = {
                            recyclePointLocationHint = it
                        },
                        label = { Text(text = "Подсказка (рядом с чем находится)") },
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.QuestionMark,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = recyclePointContacts,
                        onValueChange = {
                            recyclePointContacts = it
                        },
                        label = { Text(text = "Контакты") },
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Contacts,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = recyclePointWorkingHours,
                        onValueChange = {
                            recyclePointWorkingHours = it
                        },
                        label = { Text(text = "Время работы") },
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    DefaultFilledButton(
                        text = "Добавить",
                        onClick = {
                            onAddRecyclePoint(
                                recyclePointTitle,
                                recyclePointDescription,
                                recyclePointLocationHint,
                                recyclePointContacts,
                                recyclePointWorkingHours
                            )
                        },
                    )
                }
            }
        ) {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun RecyclePointManagerBottomSheetPreview() {
    RemapTheme {
        RecyclePointManagerBottomSheet(
            isBottomSheetVisible = true,
            scaffoldState = rememberBottomSheetScaffoldState(),
            initialRecyclePointAddress = "",
            onAddRecyclePoint = { _, _, _, _, _ -> }
        )
    }
}