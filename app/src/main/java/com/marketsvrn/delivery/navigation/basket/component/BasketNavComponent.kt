package com.marketsvrn.delivery.navigation.basket.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.addresschange.component.AddressChangeComponent
import com.marketsvrn.basket.component.BasketComponent
import com.marketsvrn.buyorder.component.BuyOrderComponent
import com.marketsvrn.productdetails.component.ProductDetailsComponent
import kotlinx.coroutines.flow.StateFlow

interface BasketNavComponent {
    val stack: StateFlow<ChildStack<*, Child>>

    sealed class Child {
        class BasketChild(val component: BasketComponent) : Child()
        class ProductDetailsChild(val component: ProductDetailsComponent) : Child()
        class BuyOrderChild(val component: BuyOrderComponent) : Child()
        class AddressChangeChild(val component: AddressChangeComponent) : Child()
    }
}