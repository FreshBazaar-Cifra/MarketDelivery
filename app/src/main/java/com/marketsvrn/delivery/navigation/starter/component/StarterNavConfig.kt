package com.marketsvrn.delivery.navigation.starter.component

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface StarterNavConfig : Parcelable {
    @Parcelize
    data object OnBoarding : StarterNavConfig

    @Parcelize
    data object Authorize : StarterNavConfig
}