package com.marketsvrn.delivery.navigation.basket.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.marketsvrn.addresschange.ui.AddressChangeScreen
import com.marketsvrn.basket.ui.BasketScreen
import com.marketsvrn.buyorder.ui.BuyOrderScreen
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent.Child.AddressChangeChild
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent.Child.BasketChild
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent.Child.BuyOrderChild
import com.marketsvrn.delivery.navigation.basket.component.BasketNavComponent.Child.ProductDetailsChild
import com.marketsvrn.designsystem.util.screenModifier
import com.marketsvrn.productdetails.ui.ProductDetailsScreen

@Composable
fun BasketNavContent(
    component: BasketNavComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsStateWithLifecycle()
    Scaffold { paddingValues ->
        Children(
            stack = stack,
            modifier = modifier.padding(paddingValues),
            animation = stackAnimation(fade() + scale()),
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is BasketChild -> BasketScreen(
                    component = child.component, Modifier.screenModifier()
                )

                is BuyOrderChild -> BuyOrderScreen(
                    component = child.component, Modifier.screenModifier()
                )

                is ProductDetailsChild -> ProductDetailsScreen(
                    component = child.component, Modifier.screenModifier()
                )

                is AddressChangeChild -> AddressChangeScreen(
                    component = child.component, Modifier.screenModifier()
                )
            }
        }
    }
}