package com.marketsvrn.delivery.navigation.home.component

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface HomeNavConfig : Parcelable {
    @Parcelize
    data object MarketSelect : HomeNavConfig

    @Parcelize
    data class PlaceSelect(val marketId: Int) : HomeNavConfig

    @Parcelize
    data class ProductsList(val placeId: Int) : HomeNavConfig

    @Parcelize
    data class ProductDetails(val productId: Int) : HomeNavConfig
}