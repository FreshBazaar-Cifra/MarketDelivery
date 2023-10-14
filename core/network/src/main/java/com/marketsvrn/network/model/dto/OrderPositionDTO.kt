package com.marketsvrn.network.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderPositionDTO(
    val productId: Int,
    val count: Int,
)
