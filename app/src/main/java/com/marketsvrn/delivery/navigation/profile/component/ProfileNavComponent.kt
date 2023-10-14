package com.marketsvrn.delivery.navigation.profile.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.marketsvrn.delivery.navigation.authorize.component.AuthorizeNavComponent
import com.marketsvrn.profile.component.ProfileComponent
import com.marketsvrn.profilesettings.component.ProfileSettingsComponent
import kotlinx.coroutines.flow.StateFlow

interface ProfileNavComponent {
    val stack: StateFlow<ChildStack<*, Child>>

    sealed class Child {
        class ProfileChild(val component: ProfileComponent) : Child()
        class AuthorizeChild(val component: AuthorizeNavComponent) : Child()
        class ProfileSettingsChild(val component: ProfileSettingsComponent) : Child()
    }
}