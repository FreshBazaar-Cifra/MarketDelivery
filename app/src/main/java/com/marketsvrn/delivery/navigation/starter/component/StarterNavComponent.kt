package com.marketsvrn.delivery.navigation.starter.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.delivery.navigation.authorize.component.AuthorizeNavComponent
import com.marketsvrn.onboarding.component.OnBoardingComponent
import kotlinx.coroutines.flow.StateFlow

interface StarterNavComponent {
    val stack: StateFlow<ChildStack<*, Child>>
    sealed class Child {
        class OnBoardingChild(val component: OnBoardingComponent) : Child()
        class AuthorizeChild(val component: AuthorizeNavComponent) : Child()
    }
}