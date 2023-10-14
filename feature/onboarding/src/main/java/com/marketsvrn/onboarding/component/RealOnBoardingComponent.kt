package com.marketsvrn.onboarding.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.marketsvrn.designsystem.util.BaseComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RealOnBoardingComponent(
    componentContext: ComponentContext,
    navigateToLoginRegister: () -> Unit
) : OnBoardingComponent, BaseComponent(componentContext) {
    private val _page = MutableStateFlow(0)
    override val page: StateFlow<Int>
        get() = _page

    override fun nextPage() {
        _page.update { it + 1 }
    }

    override fun prevPage() {
        _page.update { it - 1 }
    }


    init {
        lifecycle.doOnCreate(navigateToLoginRegister)
    }
}