package com.marketsvrn.delivery.navigation.orders.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.marketsvrn.delivery.navigation.orders.component.OrdersNavComponent
import com.marketsvrn.designsystem.util.screenModifier
import com.marketsvrn.orderdetails.ui.OrderDetailsScreen
import com.marketsvrn.orders.ui.OrdersScreen

@Composable
fun OrdersNavContent(
    component: OrdersNavComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsStateWithLifecycle()
    Children(
        stack = stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is OrdersNavComponent.Child.OrderDetailsChild -> OrderDetailsScreen(
                component = child.component, Modifier.screenModifier()
            )

            is OrdersNavComponent.Child.OrdersChild -> OrdersScreen(
                component = child.component, Modifier.screenModifier()
            )
        }
    }
}