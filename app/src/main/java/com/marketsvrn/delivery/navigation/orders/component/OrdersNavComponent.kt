package com.marketsvrn.delivery.navigation.orders.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.orderdetails.component.OrderDetailsComponent
import com.marketsvrn.orders.component.OrdersComponent
import kotlinx.coroutines.flow.StateFlow

interface OrdersNavComponent {
    val stack: StateFlow<ChildStack<*, Child>>

    sealed class Child {
        class OrdersChild(val component: OrdersComponent) : Child()
        class OrderDetailsChild(val component: OrderDetailsComponent) : Child()
    }
}