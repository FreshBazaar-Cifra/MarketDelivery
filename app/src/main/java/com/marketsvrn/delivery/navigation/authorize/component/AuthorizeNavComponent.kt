package com.marketsvrn.delivery.navigation.authorize.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.login.component.LoginComponent
import com.marketsvrn.register.component.RegisterComponent
import kotlinx.coroutines.flow.StateFlow

interface AuthorizeNavComponent {
    val stack: StateFlow<ChildStack<*, Child>>

    sealed class Child {
        class LoginChild(val component: LoginComponent) : Child()
        class RegisterChild(val component: RegisterComponent) : Child()
    }
}