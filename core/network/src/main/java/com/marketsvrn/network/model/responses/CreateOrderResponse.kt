package com.marketsvrn.network.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderResponse(
    val url: String,
    val amount: Int,
)
