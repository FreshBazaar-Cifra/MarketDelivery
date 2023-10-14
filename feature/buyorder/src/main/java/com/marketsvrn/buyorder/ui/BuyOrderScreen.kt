package com.marketsvrn.buyorder.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marketsvrn.buyorder.component.BuyOrderComponent
import com.marketsvrn.buyorder.component.FakeBuyOrderComponent
import com.marketsvrn.designsystem.theme.DeliveryTheme

@Composable
fun BuyOrderScreen(
    component: BuyOrderComponent,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text("Покупка заказа")
    }
}

@Preview
@Composable
fun BuyOrderPreview() {
    DeliveryTheme {
        BuyOrderScreen(component = FakeBuyOrderComponent())
    }
}