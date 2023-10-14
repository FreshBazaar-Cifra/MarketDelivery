package com.marketsvrn.orders.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marketsvrn.designsystem.common.ErrorView
import com.marketsvrn.designsystem.common.LoadingView
import com.marketsvrn.designsystem.common.ResourceView
import com.marketsvrn.designsystem.common.ScreenHeaderText
import com.marketsvrn.designsystem.extendedtheme.ExtendedTheme
import com.marketsvrn.designsystem.theme.DeliveryTheme
import com.marketsvrn.orders.component.FakeOrdersComponent
import com.marketsvrn.orders.component.OrdersComponent

@Composable
fun OrdersScreen(
    component: OrdersComponent,
    modifier: Modifier = Modifier
) {
    val ordersSorted = component.orders.collectAsStateWithLifecycle()
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .statusBarsPadding()
            .padding(top = 20.dp)
            .padding(horizontal = 16.dp)
            .waterfallPadding()
    ) {
        ScreenHeaderText(text = "Мои заказы")
        ResourceView(
            loadingView = {
                LoadingView(
                    modifier = Modifier.fillMaxSize()
                )
            },
            errorView = {
                ErrorView(
                    message = it!!,
                    onRetry = {
                        component.refreshOrders()
                    },
                    modifier = Modifier.fillMaxSize()
                )
            },
            successView = {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(it.keys.size) { index ->
                        DateHeading(
                            text = it.keys.elementAt(index),
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            it[it.keys.elementAt(index)]?.forEach {
                                OrderCard(
                                    order = it,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    selectOrder = component::selectOrder
                                )
                            }

                        }

                    }
                }
            },
            resource = ordersSorted.value,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun DateHeading(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        modifier = modifier,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = ExtendedTheme.colors.onContainer
    )
}

@Preview(showBackground = true)
@Composable
fun OrdersPreview() {
    DeliveryTheme {
        OrdersScreen(component = FakeOrdersComponent())
    }
}