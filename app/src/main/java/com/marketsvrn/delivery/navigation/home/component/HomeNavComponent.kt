package com.marketsvrn.delivery.navigation.home.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.marketselect.component.MarketSelectComponent
import com.marketsvrn.placeselect.component.PlaceSelectComponent
import com.marketsvrn.productdetails.component.ProductDetailsComponent
import com.marketsvrn.productslist.component.ProductsListComponent
import kotlinx.coroutines.flow.StateFlow

interface HomeNavComponent {
    val stack: StateFlow<ChildStack<*, Child>>

    sealed class Child {
        class MarketSelectChild(val component: MarketSelectComponent) : Child()
        class PlaceSelectChild(val component: PlaceSelectComponent) : Child()
        class ProductsListChild(val component: ProductsListComponent) : Child()
        class ProductDetailsChild(val component: ProductDetailsComponent) : Child()
    }
}