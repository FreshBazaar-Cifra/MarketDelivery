package com.marketsvrn.network.model.dto

import com.marketsvrn.model.Address
import com.marketsvrn.model.Order
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class OrderDTO(
    val id: Int,
    val date: String,
    val market: MarketDTO,
    val status: String,
    val deliveryman: DeliveryManDTO?,
    val price: Float,
    val deliveryPrice: Float,
    val promocode: PromoCodeDTO?,
    val address: AddressDTO,
    val total: Float,
    val positions: List<PositionDTO>
) {
    fun asDomainModel(): Order {
        return Order(
            id = id,
            market = market.asDomainModel(),
            date = LocalDateTime.parse(date.replace(" ", "T")),
            status = status,
            deliveryMan = deliveryman?.asDomainModel(),
            price = price,
            deliveryPrice = deliveryPrice,
            promocode = promocode?.asDomainModel(),
            total = total,
            address = Address.getStub(),
            positions = positions.map { it.asDomainModel() }
        )
    }
}
