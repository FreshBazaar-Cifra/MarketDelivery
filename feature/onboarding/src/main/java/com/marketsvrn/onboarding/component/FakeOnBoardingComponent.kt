package com.marketsvrn.onboarding.component

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeOnBoardingComponent: OnBoardingComponent {
    override val page: StateFlow<Int>
        get() = MutableStateFlow(0)

    override fun nextPage() {
    }

    override fun prevPage() {
    }
}