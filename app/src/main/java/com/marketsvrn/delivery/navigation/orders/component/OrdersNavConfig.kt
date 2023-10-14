package com.marketsvrn.delivery.navigation.orders.component

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface OrdersNavConfig : Parcelable {
    @Parcelize
    data object Orders : OrdersNavConfig

    @Parcelize
    data class OrderDetails(val id: Int) : OrdersNavConfig
}