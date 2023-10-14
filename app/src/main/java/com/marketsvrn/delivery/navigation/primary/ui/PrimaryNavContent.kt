package com.marketsvrn.delivery.navigation.primary.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.marketsvrn.delivery.navigation.basket.ui.BasketNavContent
import com.marketsvrn.delivery.navigation.favorites.ui.FavoritesNavContent
import com.marketsvrn.delivery.navigation.home.ui.HomeNavContent
import com.marketsvrn.delivery.navigation.orders.ui.OrdersNavContent
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavComponent
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavComponent.Child.BasketNavChild
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavComponent.Child.CreateOrderNavChild
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavComponent.Child.FavoritesChild
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavComponent.Child.OrdersNavChild
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavComponent.Child.ProfileNavChild
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavConfig
import com.marketsvrn.delivery.navigation.profile.ui.ProfileNavContent
import com.marketsvrn.designsystem.util.screenModifier


@Composable
fun PrimaryNavContent(
    component: PrimaryNavComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsStateWithLifecycle()
    val bottomNavItems = listOf(
        BottomNavBarItem(
            selected = stack.active.configuration is PrimaryNavConfig.CreateOrderNav,
            onClick = {
                component.navigateToCreateOrder()
            },
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            labelText = "Главная",
            accessibilityText = "Главный раздел",
            config = PrimaryNavConfig.CreateOrderNav
        ), BottomNavBarItem(
            selected = stack.active.configuration is PrimaryNavConfig.ProfileNav,
            onClick = {
                component.navigateToProfile()
            },
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            labelText = "Профиль",
            accessibilityText = "Раздел с профилем",
            config = PrimaryNavConfig.ProfileNav
        ), BottomNavBarItem(
            selected = stack.active.configuration is PrimaryNavConfig.BasketNav,
            onClick = {
                component.navigateToBasket()
            },
            selectedIcon = Icons.Filled.ShoppingBasket,
            unselectedIcon = Icons.Outlined.ShoppingBasket,
            labelText = "Корзина",
            accessibilityText = "Раздел с корзиной",
            config = PrimaryNavConfig.BasketNav
        ), BottomNavBarItem(
            selected = stack.active.configuration is PrimaryNavConfig.FavoritesNav,
            onClick = {
                component.navigateToFavoritesNav()
            },
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.Favorite,
            labelText = "Избранное",
            accessibilityText = "Раздел с избранными товарами",
            config = PrimaryNavConfig.FavoritesNav
        ), BottomNavBarItem(
            selected = stack.active.configuration is PrimaryNavConfig.OrdersNav,
            onClick = {
                component.navigateToOrdersNav()
            },
            selectedIcon = Icons.Filled.Update,
            unselectedIcon = Icons.Outlined.Update,
            labelText = "Заказы",
            accessibilityText = "Раздел с заказами",
            config = PrimaryNavConfig.OrdersNav
        )
    )
    Scaffold(
        bottomBar = {
            PrimaryNavBar(
                items = bottomNavItems,
                currentDestination = stack.active.configuration,
                modifier = Modifier.navigationBarsPadding()
            )
        }, contentWindowInsets = WindowInsets.navigationBars
    ) { paddingValues ->
        Children(
            stack = stack,
            modifier = modifier.padding(paddingValues),
            animation = stackAnimation(fade()),
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is CreateOrderNavChild -> HomeNavContent(
                    component = child.component, Modifier.screenModifier()
                )

                is BasketNavChild -> BasketNavContent(
                    component = child.component, Modifier.screenModifier()
                )

                is ProfileNavChild -> ProfileNavContent(
                    component = child.component, Modifier.screenModifier()
                )

                is OrdersNavChild -> OrdersNavContent(
                    component = child.component, Modifier.screenModifier()
                )

                is FavoritesChild -> FavoritesNavContent(
                    component = child.component, Modifier.screenModifier()
                )
            }
        }
    }
}