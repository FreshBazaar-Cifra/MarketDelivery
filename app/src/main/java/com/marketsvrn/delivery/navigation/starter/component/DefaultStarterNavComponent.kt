package com.marketsvrn.delivery.navigation.starter.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.marketsvrn.delivery.navigation.authorize.component.DefaultAuthorizeNavComponent
import com.marketsvrn.delivery.navigation.starter.component.StarterNavComponent.Child.AuthorizeChild
import com.marketsvrn.delivery.navigation.starter.component.StarterNavComponent.Child.OnBoardingChild
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import com.marketsvrn.onboarding.component.OnBoardingComponent
import com.marketsvrn.onboarding.component.RealOnBoardingComponent
import kotlinx.coroutines.flow.StateFlow

class DefaultStarterNavComponent(
    componentContext: ComponentContext,
    val navigateToLoggedIn: () -> Unit
) : StarterNavComponent, BaseComponent(componentContext){
    private val navigation = StackNavigation<StarterNavConfig>()

    private val _stack: StateFlow<ChildStack<StarterNavConfig, StarterNavComponent.Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = StarterNavConfig.OnBoarding,
            handleBackButton = true,
            childFactory = ::child,
            key = "StarterChildStack"
        ).toStateFlow(lifecycle)

    override val stack: StateFlow<ChildStack<*, StarterNavComponent.Child>> = _stack

    private fun child(config: StarterNavConfig, componentContext: ComponentContext): StarterNavComponent.Child {
        return when (config) {
            is StarterNavConfig.OnBoarding -> OnBoardingChild(
                onBoardingComponent(componentContext)
            )
            is StarterNavConfig.Authorize -> AuthorizeChild(
                DefaultAuthorizeNavComponent(
                    componentContext = componentContext,
                    onAuthSuccess = {
                        navigateToLoggedIn()
                    }
                )
            )
        }
    }

    private fun onBoardingComponent(componentContext: ComponentContext): OnBoardingComponent {
        return RealOnBoardingComponent(
            componentContext = componentContext,
            navigateToLoginRegister = {
                navigation.replaceCurrent(StarterNavConfig.Authorize)
            }
        )
    }
}
