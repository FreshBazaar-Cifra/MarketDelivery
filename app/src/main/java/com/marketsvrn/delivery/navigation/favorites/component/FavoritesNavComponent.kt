package com.marketsvrn.delivery.navigation.favorites.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.favorites.component.FavoritesComponent
import kotlinx.coroutines.flow.StateFlow

interface FavoritesNavComponent {
    val stack: StateFlow<ChildStack<*, Child>>
    sealed class Child {
        class FavoritesChild(val component: FavoritesComponent) : Child()
    }
}