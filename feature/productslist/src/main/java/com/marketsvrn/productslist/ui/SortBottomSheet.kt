package com.marketsvrn.productslist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    modifier: Modifier = Modifier,
    onSelectSortBy: (Int) -> Unit,
    onDismiss: () -> Unit,
    show: MutableState<Boolean>,
    selectedIndex: Int
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val dismissLambda: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                show.value = false
                onDismiss()
            }
        }
    }
    if (show.value) {
        ModalBottomSheet(
            onDismissRequest = dismissLambda,
            sheetState = sheetState,
            modifier = modifier,
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 50.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 20.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Сортировать",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    SortTypeItem(
                        typeName = "Чтобы было удобно",
                        typeDescription = "Расположим, как придумали сами",
                        onSelectSortBy = {
                            dismissLambda()
                            onSelectSortBy(it)
                        },
                        sortTypeIndex = 0,
                        modifier = Modifier.fillMaxWidth(),
                        checked = selectedIndex == 0
                    )
                    SortTypeItem(
                        typeName = "По скидке",
                        typeDescription = "Если ищете скидки побольше",
                        onSelectSortBy = {
                            dismissLambda()
                            onSelectSortBy(it)
                        },
                        sortTypeIndex = 1,
                        modifier = Modifier.fillMaxWidth(),
                        checked = selectedIndex == 1
                    )
                    SortTypeItem(
                        typeName = "По цене",
                        typeDescription = "Сначала покажем недорогие товары",
                        onSelectSortBy = {
                            dismissLambda()
                            onSelectSortBy(it)
                        },
                        sortTypeIndex = 2,
                        modifier = Modifier.fillMaxWidth(),
                        checked = selectedIndex == 2
                    )
                }
            }
        }
    }
}

@Composable
fun SortTypeItem(
    modifier: Modifier = Modifier,
    typeName: String,
    typeDescription: String,
    onSelectSortBy: (Int) -> Unit,
    sortTypeIndex: Int,
    checked: Boolean
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .clickable {
                onSelectSortBy(sortTypeIndex)
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row {
            Column(
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = typeName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = typeDescription,
                    fontSize = 14.sp
                )
            }
            Checkbox(
                checked = checked,
                enabled = true,
                onCheckedChange = {
                    onSelectSortBy(sortTypeIndex)
                },
                colors = CheckboxDefaults.colors(
                    disabledUncheckedColor = ExtendedTheme.colors.grayText,
                    disabledCheckedColor = ExtendedTheme.colors.brightGreen,
                    uncheckedColor = ExtendedTheme.colors.grayText,
                    checkedColor = ExtendedTheme.colors.brightGreen,
                    checkmarkColor = ExtendedTheme.colors.container
                ),
                modifier = Modifier.clip(CircleShape)
            )
        }
        Divider(
            thickness = Dp.Hairline,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}