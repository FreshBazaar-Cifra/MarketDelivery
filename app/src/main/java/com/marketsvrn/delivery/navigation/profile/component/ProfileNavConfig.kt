package com.marketsvrn.delivery.navigation.profile.component

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed interface ProfileNavConfig : Parcelable {

    @Parcelize
    data object Profile : ProfileNavConfig

    @Parcelize
    data object Authorize : ProfileNavConfig

    @Parcelize
    data object ProfileSettings : ProfileNavConfig
}