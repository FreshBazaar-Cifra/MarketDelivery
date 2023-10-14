package com.marketsvrn.delivery.navigation.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.delivery.navigation.primary.component.PrimaryNavComponent
import com.marketsvrn.delivery.navigation.starter.component.StarterNavComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {
    val stack: StateFlow<ChildStack<*, Child>>
    sealed class Child {
        class StarterNavChild(val component: StarterNavComponent) : Child()
        class PrimaryNavChild(val component: PrimaryNavComponent) : Child()
    }
}