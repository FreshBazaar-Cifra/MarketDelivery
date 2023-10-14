package com.marketsvrn.basket.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marketsvrn.basket.component.BasketComponent
import com.marketsvrn.basket.component.FakeBasketComponent
import com.marketsvrn.designsystem.theme.DeliveryTheme

@Composable
fun BasketScreen(
    component: BasketComponent,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Корзина")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BasketScreenPreview() {
    DeliveryTheme {
        BasketScreen(component = FakeBasketComponent())
    }
}