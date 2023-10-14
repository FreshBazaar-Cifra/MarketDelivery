package com.marketsvrn.delivery.navigation.basket.component

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface BasketNavConfig : Parcelable {
    @Parcelize
    data object Basket : BasketNavConfig

    @Parcelize
    data class ProductDetails(val productId: Int) : BasketNavConfig

    @Parcelize
    data object AddressChange : BasketNavConfig

    @Parcelize
    data object BuyOrder : BasketNavConfig
}