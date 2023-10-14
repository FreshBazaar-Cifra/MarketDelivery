package com.marketsvrn.delivery.navigation.primary.component

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface PrimaryNavConfig : Parcelable {
    @Parcelize
    data object ProfileNav : PrimaryNavConfig

    @Parcelize
    data object BasketNav : PrimaryNavConfig

    @Parcelize
    data object CreateOrderNav : PrimaryNavConfig

    @Parcelize
    data object OrdersNav : PrimaryNavConfig

    @Parcelize
    data object FavoritesNav : PrimaryNavConfig
}