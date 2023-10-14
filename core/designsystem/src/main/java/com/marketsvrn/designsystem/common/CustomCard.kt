package com.marketsvrn.designsystem.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    OutlinedCard(
        modifier = modifier,
        onClick = onClick,
        elevation = CardDefaults.outlinedCardElevation(
            0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp
        ),
        border = BorderStroke(2.dp, Color(0xFF198A32)),
        shape = RoundedCornerShape(30.dp)
    ) {
        content()
    }
}