package com.marketsvrn.delivery.navigation.profile.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.marketsvrn.delivery.navigation.authorize.component.AuthorizeNavComponent
import com.marketsvrn.delivery.navigation.authorize.component.DefaultAuthorizeNavComponent
import com.marketsvrn.delivery.navigation.profile.component.ProfileNavComponent.Child
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import com.marketsvrn.profile.component.ProfileComponent
import com.marketsvrn.profile.component.RealProfileComponent
import com.marketsvrn.profilesettings.component.ProfileSettingsComponent
import com.marketsvrn.profilesettings.component.RealProfileSettingsComponent
import kotlinx.coroutines.flow.StateFlow

class DefaultProfileNavComponent(
    componentContext: ComponentContext,
    private val onLogOutAction: () -> Unit
) : ProfileNavComponent, BaseComponent(componentContext) {
    private val navigation = StackNavigation<ProfileNavConfig>()

    private val _stack: StateFlow<ChildStack<ProfileNavConfig, Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = ProfileNavConfig.Profile,
            handleBackButton = true,
            childFactory = ::child,
            key = "ProfileChildStack"
        ).toStateFlow(lifecycle)

    override val stack: StateFlow<ChildStack<*, Child>> = _stack

    private fun child(
        config: ProfileNavConfig,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            is ProfileNavConfig.Profile -> Child.ProfileChild(
                createProfileComponent(componentContext)
            )

            is ProfileNavConfig.ProfileSettings -> Child.ProfileSettingsChild(
                createProfileSettingsComponent(componentContext)
            )

            is ProfileNavConfig.Authorize -> Child.AuthorizeChild(
                authorizeComponent(componentContext)
            )
        }
    }

    private fun createProfileSettingsComponent(
        componentContext: ComponentContext
    ): ProfileSettingsComponent {
        return RealProfileSettingsComponent(componentContext)
    }

    private fun authorizeComponent(
        componentContext: ComponentContext
    ): AuthorizeNavComponent {
        return DefaultAuthorizeNavComponent(
            componentContext = componentContext,
            onAuthSuccess = {

            }
        )
    }

    private fun createProfileComponent(
        componentContext: ComponentContext
    ): ProfileComponent {
        return RealProfileComponent(
            componentContext = componentContext,
            onLogOutAction = {
                onLogOutAction()
            }
        )
    }


}