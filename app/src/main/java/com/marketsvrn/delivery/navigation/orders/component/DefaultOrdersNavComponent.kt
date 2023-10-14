package com.marketsvrn.delivery.navigation.orders.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.marketsvrn.delivery.navigation.orders.component.OrdersNavComponent.Child.OrderDetailsChild
import com.marketsvrn.delivery.navigation.orders.component.OrdersNavComponent.Child.OrdersChild
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import com.marketsvrn.orderdetails.component.RealOrderDetailsComponent
import com.marketsvrn.orders.component.RealOrdersComponent
import kotlinx.coroutines.flow.StateFlow

class DefaultOrdersNavComponent(
    componentContext: ComponentContext
) : OrdersNavComponent, BaseComponent(componentContext) {
    private val navigation = StackNavigation<OrdersNavConfig>()
    private val _stack: StateFlow<ChildStack<*, OrdersNavComponent.Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = OrdersNavConfig.Orders,
            handleBackButton = true,
            childFactory = ::child,
            key = "OrdersChildStack"
        ).toStateFlow(lifecycle)

    override val stack: StateFlow<ChildStack<*, OrdersNavComponent.Child>> = _stack

    private fun child(
        config: OrdersNavConfig,
        componentContext: ComponentContext
    ): OrdersNavComponent.Child {
        return when (config) {
            is OrdersNavConfig.Orders -> OrdersChild(
                RealOrdersComponent(
                    componentContext = componentContext,
                    navigateToOrderDetails = {
                        navigation.push(OrdersNavConfig.OrderDetails(it))
                    }
                )
            )

            is OrdersNavConfig.OrderDetails -> OrderDetailsChild(
                RealOrderDetailsComponent(componentContext, config.id)
            )
        }
    }
}