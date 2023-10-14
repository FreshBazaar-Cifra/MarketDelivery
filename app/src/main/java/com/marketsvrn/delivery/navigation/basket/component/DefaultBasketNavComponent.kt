package com.marketsvrn.delivery.navigation.basket.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.marketsvrn.addresschange.component.AddressChangeComponent
import com.marketsvrn.addresschange.component.RealAddressChangeComponent
import com.marketsvrn.basket.component.BasketComponent
import com.marketsvrn.basket.component.RealBasketComponent
import com.marketsvrn.buyorder.component.BuyOrderComponent
import com.marketsvrn.buyorder.component.RealBuyOrderComponent
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent.Child.AddressChangeChild
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent.Child.BasketChild
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent.Child.BuyOrderChild
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent.Child.ProductDetailsChild
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import com.marketsvrn.productdetails.component.ProductDetailsComponent
import com.marketsvrn.productdetails.component.RealProductDetailsComponent
import kotlinx.coroutines.flow.StateFlow

class DefaultBasketNavComponent(
    componentContext: ComponentContext
) : BasketNavComponent, BaseComponent(componentContext) {
    private val navigation = StackNavigation<BasketNavConfig>()
    private val _stack: StateFlow<ChildStack<*, BasketNavComponent.Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = BasketNavConfig.Basket,
            handleBackButton = true,
            childFactory = ::child,
            key = "BasketChildStack"
        ).toStateFlow(lifecycle)
    override val stack: StateFlow<ChildStack<*, BasketNavComponent.Child>> = _stack

    private fun child(
        config: BasketNavConfig,
        componentContext: ComponentContext
    ): BasketNavComponent.Child {
        return when (config) {
            is BasketNavConfig.Basket -> BasketChild(
                basketComponent(componentContext)
            )

            is BasketNavConfig.BuyOrder -> BuyOrderChild(
                buyOrderComponent(componentContext)
            )

            is BasketNavConfig.ProductDetails -> ProductDetailsChild(
                productDetailsComponent(componentContext, config)
            )

            is BasketNavConfig.AddressChange -> AddressChangeChild(
                addressChangeComponent(componentContext)
            )
        }
    }

    private fun addressChangeComponent(componentContext: ComponentContext): AddressChangeComponent {
        return RealAddressChangeComponent(
            componentContext = componentContext
        )
    }

    private fun basketComponent(componentContext: ComponentContext): BasketComponent {
        return RealBasketComponent(
            componentContext = componentContext
        )
    }

    private fun buyOrderComponent(componentContext: ComponentContext): BuyOrderComponent {
        return RealBuyOrderComponent(
            componentContext = componentContext
        )
    }

    private fun productDetailsComponent(
        componentContext: ComponentContext,
        config: BasketNavConfig.ProductDetails
    ): ProductDetailsComponent {
        return RealProductDetailsComponent(
            componentContext = componentContext,
            productId = config.productId
        )
    }

}