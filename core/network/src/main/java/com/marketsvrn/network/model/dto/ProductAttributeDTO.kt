package com.marketsvrn.network.model.dto

import com.marketsvrn.model.Attribute
import kotlinx.serialization.Serializable

@Serializable
data class ProductAttributeDTO(
    val key: String,
    val value: String,
    val productId: Int
) {
    fun asDomainModel(): Attribute =
        Attribute(key, value, productId)
}
