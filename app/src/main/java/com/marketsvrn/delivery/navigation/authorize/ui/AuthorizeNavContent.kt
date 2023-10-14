package com.marketsvrn.delivery.navigation.authorize.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.marketsvrn.delivery.navigation.authorize.component.AuthorizeNavComponent
import com.marketsvrn.delivery.navigation.authorize.component.AuthorizeNavComponent.Child.LoginChild
import com.marketsvrn.delivery.navigation.authorize.component.AuthorizeNavComponent.Child.RegisterChild
import com.marketsvrn.designsystem.util.screenModifier
import com.marketsvrn.login.ui.LoginScreen
import com.marketsvrn.register.ui.RegisterScreen

@Composable
fun AuthorizeNavContent(
    component: AuthorizeNavComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsStateWithLifecycle()
    Children(
        stack = stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale(backFactor = 0.8f)),
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is LoginChild -> LoginScreen(component = child.component, Modifier.screenModifier())
            is RegisterChild -> RegisterScreen(component = child.component, Modifier.screenModifier())
        }
    }
}