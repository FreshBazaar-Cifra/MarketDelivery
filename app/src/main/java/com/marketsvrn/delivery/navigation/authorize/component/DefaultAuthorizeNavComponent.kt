package com.marketsvrn.delivery.navigation.authorize.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.marketsvrn.common.SplashScreenSingleton
import com.marketsvrn.data.repository.user.UserRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.designsystem.util.toStateFlow
import com.marketsvrn.login.component.LoginComponent
import com.marketsvrn.login.component.RealLoginComponent
import com.marketsvrn.register.component.RealRegisterComponent
import com.marketsvrn.register.component.RegisterComponent
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.inject

class DefaultAuthorizeNavComponent(
    private val onAuthSuccess: () -> Unit,
    componentContext: ComponentContext
): AuthorizeNavComponent, BaseComponent(componentContext) {
    private val repo: UserRepository by inject()
    private val splash: SplashScreenSingleton by inject()

    private val navigation = StackNavigation<AuthorizeNavConfig>()
    private val _stack: StateFlow<ChildStack<*, AuthorizeNavComponent.Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = AuthorizeNavConfig.Login,
            handleBackButton = true,
            childFactory = ::child,
            key = "AuthChildStack"
        ).toStateFlow(lifecycle)
    override val stack: StateFlow<ChildStack<*, AuthorizeNavComponent.Child>> = _stack

    private fun child(
        config: AuthorizeNavConfig,
        componentContext: ComponentContext
    ): AuthorizeNavComponent.Child {
        return when (config) {
            is AuthorizeNavConfig.Login -> AuthorizeNavComponent.Child.LoginChild(
                loginComponent(componentContext)
            )
            is AuthorizeNavConfig.Register -> AuthorizeNavComponent.Child.RegisterChild(
                registerComponent(componentContext)
            )
        }
    }

    private fun loginComponent(
        componentContext: ComponentContext
    ): LoginComponent {
        return RealLoginComponent(
            componentContext = componentContext,
            onLogin = {
                onAuthSuccess()
            },
            onNavigateRegister = {
                navigation.bringToFront(AuthorizeNavConfig.Register)
            }
        )
    }

    private fun registerComponent(
        componentContext: ComponentContext
    ): RegisterComponent {
        return RealRegisterComponent(
            componentContext = componentContext,
            onRegister = {
                onAuthSuccess()
            },
            onNavigateLogin = {
                navigation.bringToFront(AuthorizeNavConfig.Login)
            }
        )
    }

    init {
        lifecycle.doOnCreate {
            val hasLogin = repo.hasLogin()
            if (hasLogin) {
                onAuthSuccess()
            } else splash.setReady()
        }
    }
}