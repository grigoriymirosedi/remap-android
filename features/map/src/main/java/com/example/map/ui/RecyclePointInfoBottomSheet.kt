package com.example.map.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PermContactCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.models.RecyclePoint
import com.example.core.util.toCategoryType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RecyclePointInfoBottomSheet(
    modifier: Modifier = Modifier.fillMaxWidth(),
    recyclePoint: RecyclePoint,
    bottomSheetState: SheetState,
    onDismissRequest: () -> Unit,
) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context: Context = LocalContext.current

    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            com.example.core.ui.TitleText(
                modifier = Modifier.fillMaxWidth(),
                text = recyclePoint.name
            )
            Spacer(modifier = Modifier.size(8.dp))
            // TODO: implement it later
//            Image(
//                modifier = Modifier.fillMaxWidth().height(300.dp), contentScale = ContentScale.Crop,
//                painter = painterResource(id = R.drawable.recycle_point_sample_image),
//                contentDescription = null
//            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = recyclePoint.description,
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.size(6.dp))
            com.example.core.ui.TextWithIcon(
                modifier = Modifier.fillMaxWidth(),
                text = recyclePoint.address,
                icon = Icons.Outlined.LocationOn,
                contentColor = Color.Gray,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.size(2.dp))
            com.example.core.ui.TextWithIcon(
                modifier = Modifier.fillMaxWidth(),
                text = recyclePoint.working_hours,
                icon = Icons.Outlined.CalendarMonth,
                contentColor = Color.Gray,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.size(2.dp))
            com.example.core.ui.TextWithIcon(
                modifier = Modifier.fillMaxWidth(),
                text = recyclePoint.contacts,
                icon = Icons.Outlined.PermContactCalendar,
                contentColor = Color.Gray,
                fontSize = 16.sp,
            )
            FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                recyclePoint.categories.forEach {
                    SuggestionChip(onClick = { /*TODO*/ }, label = { Text(text = it.toCategoryType(), fontSize = 14.sp) })
                }
            }
            Spacer(modifier = Modifier.size(4.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                Button(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(recyclePoint.address))
                        Toast.makeText(context, "Адрес скопирован!", Toast.LENGTH_SHORT).show()
                    }) {
                    com.example.core.ui.TextWithIcon(
                        text = "Скопировать адрес",
                        icon = Icons.Filled.ContentCopy,
                        contentColor = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RecyclePointInfoBottomSheetPreview() {
    val bottomSheetState = rememberModalBottomSheetState()
    RecyclePointInfoBottomSheet(
        modifier = Modifier.fillMaxWidth(),
        recyclePoint = com.example.core.mock.MockData.mockRecyclePointData,
        bottomSheetState = bottomSheetState,
        onDismissRequest = {}
    )
}