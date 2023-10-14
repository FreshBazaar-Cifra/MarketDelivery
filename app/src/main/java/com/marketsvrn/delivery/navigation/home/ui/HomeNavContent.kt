package com.marketsvrn.delivery.navigation.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.marketsvrn.delivery.navigation.home.component.HomeNavComponent
import com.marketsvrn.delivery.navigation.home.component.HomeNavComponent.Child.MarketSelectChild
import com.marketsvrn.delivery.navigation.home.component.HomeNavComponent.Child.PlaceSelectChild
import com.marketsvrn.delivery.navigation.home.component.HomeNavComponent.Child.ProductDetailsChild
import com.marketsvrn.delivery.navigation.home.component.HomeNavComponent.Child.ProductsListChild
import com.marketsvrn.marketselect.ui.MarketSelectScreen
import com.marketsvrn.placeselect.ui.PlaceSelectScreen
import com.marketsvrn.productdetails.ui.ProductDetailsScreen
import com.marketsvrn.productslist.ui.ProductsListScreen

@Composable
fun HomeNavContent(
    component: HomeNavComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsStateWithLifecycle()
    Children(
        stack = stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale(backFactor = 0.8f)),
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is MarketSelectChild -> MarketSelectScreen(
                component = child.component
            )

            is PlaceSelectChild -> PlaceSelectScreen(
                component = child.component
            )

            is ProductDetailsChild -> ProductDetailsScreen(
                component = child.component
            )

            is ProductsListChild -> ProductsListScreen(
                component = child.component
            )

        }
    }
}