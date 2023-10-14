package com.marketsvrn.delivery.navigation.root.component

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface RootConfig : Parcelable {
    @Parcelize
    data object StarterNav : RootConfig
    @Parcelize
    data object PrimaryNav : RootConfig
}