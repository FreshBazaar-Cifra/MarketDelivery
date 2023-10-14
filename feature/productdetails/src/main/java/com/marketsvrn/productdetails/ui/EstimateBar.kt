package com.marketsvrn.productdetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme

@Composable
fun EstimateBar(
    modifier: Modifier = Modifier,
    estimate: Float,
    onEstimateAction: () -> Unit
) {
    val estimateString = remember {
        "$estimate"
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = ExtendedTheme.colors.brightGreen,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = estimateString,
            fontSize = 24.sp
        )
        Text(
            text = "Оценить",
            fontSize = 24.sp,
            modifier = Modifier.clickable {
                onEstimateAction()
            },
            color = ExtendedTheme.colors.grayText
        )
    }

}