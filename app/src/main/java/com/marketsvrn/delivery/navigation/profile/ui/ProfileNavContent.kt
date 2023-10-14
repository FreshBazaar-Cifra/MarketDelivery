package com.marketsvrn.delivery.navigation.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.marketsvrn.delivery.navigation.authorize.ui.AuthorizeNavContent
import com.marketsvrn.delivery.navigation.profile.component.ProfileNavComponent
import com.marketsvrn.delivery.navigation.profile.component.ProfileNavComponent.Child.ProfileChild
import com.marketsvrn.delivery.navigation.profile.component.ProfileNavComponent.Child.ProfileSettingsChild
import com.marketsvrn.designsystem.util.screenModifier
import com.marketsvrn.profile.ui.ProfileScreen
import com.marketsvrn.profilesettings.ui.ProfileSettingsScreen

@Composable
fun ProfileNavContent(
    component: ProfileNavComponent,
    modifier: Modifier = Modifier
) {
    val stack by component.stack.collectAsStateWithLifecycle()
    Children(
        stack = stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is ProfileChild -> ProfileScreen(
                component = child.component
            )

            is ProfileSettingsChild -> ProfileSettingsScreen(
                component = child.component
            )

            is ProfileNavComponent.Child.AuthorizeChild -> AuthorizeNavContent(
                component = child.component,
                modifier = Modifier.screenModifier()
            )
        }
    }
}