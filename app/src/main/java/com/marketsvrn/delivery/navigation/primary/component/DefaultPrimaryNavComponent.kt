package com.marketsvrn.delivery.navigation.primary.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent
import com.marketsvrn.delivery.navigation.basket.component.DefaultBasketNavComponent
import com.marketsvrn.delivery.navigation.favorites.component.DefaultFavoritesNavComponent
import com.marketsvrn.delivery.navigation.favorites.component.FavoritesNavComponent
import com.marketsvrn.delivery.navigation.home.component.DefaultHomeNavComponent
import com.marketsvrn.delivery.navigation.home.component.HomeNavComponent
import com.marketsvrn.delivery.navigation.orders.component.DefaultOrdersNavComponent
import com.marketsvrn.delivery.navigation.orders.component.OrdersNavComponent
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavComponent.Child
import com.marketsvrn.delivery.navigation.profile.component.DefaultProfileNavComponent
import com.marketsvrn.delivery.navigation.profile.component.ProfileNavComponent
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import kotlinx.coroutines.flow.StateFlow

class DefaultPrimaryNavComponent(
    private val onLogOutAction: () -> Unit,
    componentContext: ComponentContext
) : PrimaryNavComponent, BaseComponent(componentContext) {
    private val navigation = StackNavigation<PrimaryNavConfig>()

    private val _stack: StateFlow<ChildStack<PrimaryNavConfig, Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = PrimaryNavConfig.CreateOrderNav,
            handleBackButton = false,
            childFactory = ::child,
            key = "PrimaryChildStack"
        ).toStateFlow(lifecycle)

    override val stack: StateFlow<ChildStack<PrimaryNavConfig, Child>> = _stack
    override fun navigateToCreateOrder() {
        navigation.bringToFront(PrimaryNavConfig.CreateOrderNav)
    }

    override fun navigateToProfile() {
        navigation.bringToFront(PrimaryNavConfig.ProfileNav)
    }

    override fun navigateToBasket() {
        navigation.bringToFront(PrimaryNavConfig.BasketNav)
    }

    override fun navigateToOrdersNav() {
        navigation.bringToFront(PrimaryNavConfig.OrdersNav)
    }

    override fun navigateToFavoritesNav() {
        navigation.bringToFront(PrimaryNavConfig.FavoritesNav)
    }

    private fun child(
        config: PrimaryNavConfig,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            is PrimaryNavConfig.CreateOrderNav -> Child.CreateOrderNavChild(
                createOrderNavComponent(componentContext)
            )

            is PrimaryNavConfig.BasketNav -> Child.BasketNavChild(
                basketNavComponent(componentContext)
            )

            is PrimaryNavConfig.ProfileNav -> Child.ProfileNavChild(
                profileNavComponent(componentContext)
            )

            is PrimaryNavConfig.OrdersNav -> Child.OrdersNavChild(
                ordersNavComponent(componentContext)
            )

            is PrimaryNavConfig.FavoritesNav -> Child.FavoritesChild(
                favoritesNavComponent(componentContext)
            )
        }
    }

    private fun favoritesNavComponent(
        componentContext: ComponentContext
    ): FavoritesNavComponent {
        return DefaultFavoritesNavComponent(
            componentContext = componentContext
        )
    }

    private fun ordersNavComponent(
        componentContext: ComponentContext
    ): OrdersNavComponent {
        return DefaultOrdersNavComponent(
            componentContext = componentContext
        )
    }

    private fun createOrderNavComponent(
        componentContext: ComponentContext
    ): HomeNavComponent {
        return DefaultHomeNavComponent(
            onMarketSelect = {
                //
            },
            componentContext = componentContext,
        )
    }

    private fun basketNavComponent(
        componentContext: ComponentContext
    ): BasketNavComponent {
        return DefaultBasketNavComponent(
            componentContext = componentContext,
        )
    }

    private fun profileNavComponent(
        componentContext: ComponentContext
    ): ProfileNavComponent {
        return DefaultProfileNavComponent(
            onLogOutAction = {
                onLogOutAction()
            },
            componentContext = componentContext,
        )
    }

}
