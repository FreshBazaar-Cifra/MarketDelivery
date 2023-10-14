package com.marketsvrn.delivery.navigation.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.marketsvrn.delivery.navigation.primary.ui.PrimaryNavContent
import com.marketsvrn.delivery.navigation.root.component.RootComponent
import com.marketsvrn.delivery.navigation.starter.ui.StarterNavContent
import com.marketsvrn.designsystem.util.screenModifier

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    val stack by component.stack.collectAsState()
    Children(
        stack = stack,
        modifier = modifier,
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is RootComponent.Child.StarterNavChild -> StarterNavContent(
                component = child.component, Modifier.screenModifier()
            )
            is RootComponent.Child.PrimaryNavChild -> PrimaryNavContent(
                component = child.component, Modifier.screenModifier()
            )
        }
    }
}