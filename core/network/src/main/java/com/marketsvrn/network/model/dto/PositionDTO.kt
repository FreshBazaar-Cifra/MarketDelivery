package com.marketsvrn.network.model.dto

import com.marketsvrn.model.Position
import kotlinx.serialization.Serializable

@Serializable
data class PositionDTO(
    val product: ProductDTO,
    val count: Int
) {
    fun asDomainModel(): Position {
        return with(this) {
            Position(
                product = product.asDomainModel(),
                count = count
            )
        }
    }
}
