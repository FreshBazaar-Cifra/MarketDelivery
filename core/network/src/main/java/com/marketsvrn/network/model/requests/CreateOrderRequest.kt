package com.marketsvrn.network.model.requests

import com.marketsvrn.network.model.dto.OrderPositionDTO
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequest(
    val addressId: Int,
    val marketId: Int,
    val positions: List<OrderPositionDTO>,
    val promocodeId: Int
)
