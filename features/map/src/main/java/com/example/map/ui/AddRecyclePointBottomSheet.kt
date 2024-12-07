package com.example.map.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.outlined.EditLocation
import androidx.compose.material.icons.outlined.FormatAlignLeft
import androidx.compose.material.icons.outlined.PermContactCalendar
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.remote.dto.RecyclePointDTO

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddRecyclePontBottomSheet(
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState,
    placeMarkDetails: String,
    latitude: Double,
    longitude: Double,
    onAddCategoryTag: (String) -> Unit,
    onRemoveCategoryTag: (String) -> Unit,
    onAddRecyclePoint: (com.example.data.remote.dto.RecyclePointDTO) -> Unit,
    onAddingCompleted: () -> Unit,
    onDismissRequest: () -> Unit,
) {

    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest
    ) {

        var placemarkName by rememberSaveable { mutableStateOf("") }
        var placemarkDescription by rememberSaveable { mutableStateOf("") }
        var placemarkContacts by rememberSaveable { mutableStateOf("") }
        var placemarkAddress by rememberSaveable { mutableStateOf(placeMarkDetails) }
        var placemarkWorkingHours by rememberSaveable { mutableStateOf("") }

        val (checkedState, onStateChange) = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            com.example.core.ui.TitleText(text = "Новая точка переработки")

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.FormatSize,
                        contentDescription = null
                    )
                },
                value = placemarkName,
                onValueChange = { placemarkName = it },
                label = { Text("Название") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.FormatAlignLeft,
                        contentDescription = null
                    )
                },
                value = placemarkDescription,
                onValueChange = { placemarkDescription = it },
                label = { Text("Описание") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.PermContactCalendar,
                        contentDescription = null
                    )
                },
                value = placemarkContacts,
                onValueChange = { placemarkContacts = it },
                label = { Text("Контакты") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.EditLocation,
                        contentDescription = null
                    )
                },
                value = placemarkAddress,
                onValueChange = { placemarkAddress = it },
                label = { Text("Адрес") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Today,
                        contentDescription = null
                    )
                },
                value = placemarkWorkingHours,
                onValueChange = { placemarkWorkingHours = it },
                label = { Text("Часы работы") }
            )

            Spacer(modifier = Modifier.size(16.dp))

            com.example.core.ui.TitleText(text = "Категории", fontSize = 20.sp)

            Spacer(modifier = Modifier.size(8.dp))

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .toggleable(
                        value = checkedState,
                        onValueChange = { onStateChange(!checkedState) },
                        role = Role.Checkbox
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start)
            )
            {
                com.example.core.models.CategoryType.values().forEach { category ->
                    com.example.core.ui.CategoryFilterChip(
                        categoryType = category,
                        onClick = { onAddCategoryTag(category.sourceName) },
                        onDismiss = { onRemoveCategoryTag(category.sourceName) }
                    )
                }
            }

            Button(
                onClick = {
                    val recyclePoint = com.example.data.remote.dto.RecyclePointDTO(
                        id = null,
                        name = placemarkName,
                        image = "",
                        description = placemarkDescription,
                        contacts = placemarkContacts,
                        latitude = latitude,
                        longitude = longitude,
                        address = placemarkAddress,
                        working_hours = placemarkWorkingHours,
                        categories = null
                    )
                    onAddRecyclePoint(recyclePoint)
                    onAddingCompleted()
                }) {
                com.example.core.ui.TextWithIcon(
                    text = "Добавить",
                    icon = Icons.Filled.Add,
                    contentColor = Color.White
                )
            }
        }
    }
}