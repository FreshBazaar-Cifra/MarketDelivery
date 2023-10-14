package com.marketsvrn.network.model.dto

import com.marketsvrn.model.Promo
import kotlinx.serialization.Serializable

@Serializable
data class PromoCodeDTO(
    val id: Int,
    val sale: Int,
    val code: String
) {
    fun asDomainModel(): Promo = Promo(sale, code)
}
