package com.marketsvrn.delivery.navigation.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.marketsvrn.common.SplashScreenSingleton
import com.marketsvrn.delivery.navigation.primary.component.DefaultPrimaryNavComponent
import com.marketsvrn.delivery.navigation.root.component.RootComponent.Child.PrimaryNavChild
import com.marketsvrn.delivery.navigation.root.component.RootComponent.Child.StarterNavChild
import com.marketsvrn.delivery.navigation.starter.component.DefaultStarterNavComponent
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, BaseComponent(componentContext){
    private val navigation = StackNavigation<RootConfig>()

    private val screenSingleton: SplashScreenSingleton by inject()

    private val _stack: StateFlow<ChildStack<RootConfig, RootComponent.Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = RootConfig.StarterNav,
            handleBackButton = false,
            childFactory = ::child,
            key = "RootChildStack"
        ).toStateFlow(lifecycle)

    override val stack: StateFlow<ChildStack<*, RootComponent.Child>> = _stack

    private fun child(config: RootConfig, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is RootConfig.StarterNav -> StarterNavChild(
                DefaultStarterNavComponent(
                    componentContext = componentContext,
                    navigateToLoggedIn = {
                        mainScope.launch {
                            navigation.replaceAll(RootConfig.PrimaryNav){
                                mainScope.launch {
                                    //delay(500L)
                                    screenSingleton.setReady()
                                }
                            }
                        }
                    }
                )
            )
            is RootConfig.PrimaryNav -> PrimaryNavChild(
                DefaultPrimaryNavComponent(
                    componentContext = componentContext,
                    onLogOutAction = {
                        mainScope.launch {
                            navigation.replaceAll(RootConfig.StarterNav)
                        }
                    }
                )
            )
        }
    }
}
