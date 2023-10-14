package com.marketsvrn.delivery.navigation.home.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.marketsvrn.delivery.navigation.home.component.HomeNavComponent.Child
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import com.marketsvrn.marketselect.component.MarketSelectComponent
import com.marketsvrn.marketselect.component.RealMarketSelectComponent
import com.marketsvrn.placeselect.component.PlaceSelectComponent
import com.marketsvrn.placeselect.component.RealPlaceSelectComponent
import com.marketsvrn.productdetails.component.ProductDetailsComponent
import com.marketsvrn.productdetails.component.RealProductDetailsComponent
import com.marketsvrn.productslist.component.ProductsListComponent
import com.marketsvrn.productslist.component.RealProductsListComponent
import kotlinx.coroutines.flow.StateFlow

class DefaultHomeNavComponent(
    val onMarketSelect: (Int) -> Unit,
    componentContext: ComponentContext,
) : HomeNavComponent, BaseComponent(componentContext) {
    private val navigation = StackNavigation<HomeNavConfig>()
    private val _stack: StateFlow<ChildStack<*, Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = HomeNavConfig.MarketSelect,
            handleBackButton = true,
            childFactory = ::child,
            key = "HomeChildStack"
        ).toStateFlow(lifecycle)
    override val stack: StateFlow<ChildStack<*, Child>> = _stack

    private fun child(
        config: HomeNavConfig,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            is HomeNavConfig.MarketSelect -> Child.MarketSelectChild(
                marketSelectComponent(componentContext)
            )

            is HomeNavConfig.PlaceSelect -> Child.PlaceSelectChild(
                placeSelectComponent(componentContext, config)
            )

            is HomeNavConfig.ProductDetails -> Child.ProductDetailsChild(
                productDetailsComponent(componentContext, config)
            )

            is HomeNavConfig.ProductsList -> Child.ProductsListChild(
                productsListComponent(componentContext, config)
            )
        }
    }

    private fun marketSelectComponent(
        componentContext: ComponentContext
    ): MarketSelectComponent {
        return RealMarketSelectComponent(
            onMarketSelect = {
                onMarketSelect(it)
                navigation.push(HomeNavConfig.PlaceSelect(it))
            },
            componentContext = componentContext
        )
    }

    private fun placeSelectComponent(
        componentContext: ComponentContext,
        config: HomeNavConfig.PlaceSelect
    ): PlaceSelectComponent {
        return RealPlaceSelectComponent(
            componentContext = componentContext,
            marketId = config.marketId,
            onPlaceSelect = {
                navigation.push(HomeNavConfig.ProductsList(it))
            }
        )
    }

    private fun productDetailsComponent(
        componentContext: ComponentContext,
        config: HomeNavConfig.ProductDetails
    ): ProductDetailsComponent {
        return RealProductDetailsComponent(
            componentContext = componentContext,
            productId = config.productId
        )
    }

    private fun productsListComponent(
        componentContext: ComponentContext,
        config: HomeNavConfig.ProductsList
    ): ProductsListComponent {
        return RealProductsListComponent(
            componentContext = componentContext,
            placeId = config.placeId,
            onProductSelect = {
                navigation.push(HomeNavConfig.ProductDetails(it))
            }
        )
    }
}