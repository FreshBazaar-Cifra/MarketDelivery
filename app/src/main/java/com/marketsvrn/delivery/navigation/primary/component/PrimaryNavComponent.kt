package com.marketsvrn.delivery.navigation.primary.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent
import com.marketsvrn.delivery.navigation.favorites.component.FavoritesNavComponent
import com.marketsvrn.delivery.navigation.home.component.HomeNavComponent
import com.marketsvrn.delivery.navigation.orders.component.OrdersNavComponent
import com.marketsvrn.delivery.navigation.profile.component.ProfileNavComponent
import kotlinx.coroutines.flow.StateFlow

interface PrimaryNavComponent {
    val stack: StateFlow<ChildStack<PrimaryNavConfig, Child>>

    sealed class Child {
        class ProfileNavChild(val component: ProfileNavComponent) : Child()
        class CreateOrderNavChild(val component: HomeNavComponent) : Child()
        class BasketNavChild(val component: BasketNavComponent) : Child()
        class OrdersNavChild(val component: OrdersNavComponent) : Child()
        class FavoritesChild(val component: FavoritesNavComponent) : Child()
    }

    fun navigateToCreateOrder()
    fun navigateToProfile()
    fun navigateToBasket()
    fun navigateToOrdersNav()
    fun navigateToFavoritesNav()
}