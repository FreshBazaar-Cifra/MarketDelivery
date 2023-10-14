package com.marketsvrn.network.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class PromoPriceResponse(
    val code: String,
    val price: Float,
)
