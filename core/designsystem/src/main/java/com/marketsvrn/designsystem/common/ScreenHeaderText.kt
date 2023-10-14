package com.marketsvrn.designsystem.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeaderText(
    modifier: Modifier = Modifier,
    text: String
){
    val textCapitalized = remember {
        text.uppercase()
    }
    Text(
        text = textCapitalized,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = modifier
    )
}