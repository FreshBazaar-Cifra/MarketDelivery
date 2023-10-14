package com.marketsvrn.delivery.navigation.favorites.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.marketsvrn.delivery.navigation.favorites.component.FavoritesNavComponent.Child
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import com.marketsvrn.favorites.component.RealFavoritesComponent
import kotlinx.coroutines.flow.StateFlow

class DefaultFavoritesNavComponent(
    componentContext: ComponentContext
): FavoritesNavComponent, BaseComponent(componentContext) {
    private val navigation = StackNavigation<FavoritesNavConfig>()

    private val _stack: StateFlow<ChildStack<*, Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = FavoritesNavConfig.Favorites,
            handleBackButton = true,
            childFactory = ::child,
            key = "FavoritesChildStack"
        ).toStateFlow(lifecycle)
    override val stack: StateFlow<ChildStack<*, Child>> = _stack

    private fun child(
        config: FavoritesNavConfig,
        componentContext: ComponentContext
    ): Child {
        return when(config) {
            is FavoritesNavConfig.Favorites -> Child.FavoritesChild(
                RealFavoritesComponent(componentContext)
            )
        }
    }


}