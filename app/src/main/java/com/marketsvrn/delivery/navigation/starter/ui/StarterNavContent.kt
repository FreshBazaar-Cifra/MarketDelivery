package com.marketsvrn.delivery.navigation.starter.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.marketsvrn.delivery.navigation.authorize.ui.AuthorizeNavContent
import com.marketsvrn.delivery.navigation.starter.component.StarterNavComponent
import com.marketsvrn.delivery.navigation.starter.component.StarterNavComponent.Child.AuthorizeChild
import com.marketsvrn.delivery.navigation.starter.component.StarterNavComponent.Child.OnBoardingChild
import com.marketsvrn.designsystem.util.screenModifier
import com.marketsvrn.onboarding.ui.OnBoardingScreen

@Composable
fun StarterNavContent(
    component: StarterNavComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsStateWithLifecycle()
    Children(
        stack = stack,
        modifier = modifier.padding(),
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is OnBoardingChild -> OnBoardingScreen(
                component = child.component, Modifier.screenModifier()
            )

            is AuthorizeChild -> AuthorizeNavContent(
                component = child.component, Modifier.screenModifier()
            )
        }
    }
}