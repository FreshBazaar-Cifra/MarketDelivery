package com.marketsvrn.onboarding.component

import kotlinx.coroutines.flow.StateFlow

interface OnBoardingComponent {
    val page: StateFlow<Int>
    fun nextPage()
    fun prevPage()
}