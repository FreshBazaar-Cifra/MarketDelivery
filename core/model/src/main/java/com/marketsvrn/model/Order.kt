package com.marketsvrn.model

import kotlinx.datetime.LocalDateTime
import java.time.Month

data class Order(
    val id: Int,
    val market: Market,
    val address: Address,
    val date: LocalDateTime,
    val status: String,
    val deliveryMan: DeliveryMan?,
    val price: Float,
    val deliveryPrice: Float,
    val promocode: Promo?,
    val total: Float,
    val positions: List<Position>,
) {
    companion object {
        fun getStub(): Order {
            return Order(
                id = 0,
                date = LocalDateTime(
                    year = 2023,
                    month = Month.AUGUST,
                    dayOfMonth = 23,
                    hour = 16,
                    minute = 22,
                    second = 17
                ),
                status = "",
                deliveryMan = null,
                price = 777.22f,
                deliveryPrice = 20f,
                promocode = null,
                total = 100f,
                market = Market.getStub(),
                positions = listOf(Position.getStub()),
                address = Address.getStub()
            )
        }
    }
}