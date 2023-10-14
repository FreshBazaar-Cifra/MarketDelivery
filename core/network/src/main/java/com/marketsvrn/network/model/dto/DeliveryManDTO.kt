package com.marketsvrn.network.model.dto

import com.marketsvrn.model.DeliveryMan
import kotlinx.serialization.Serializable

@Serializable
data class DeliveryManDTO(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val regDate: String,
    val phone: String
) {
    fun asDomainModel(): DeliveryMan =
        DeliveryMan(id, firstName, lastName, regDate, phone)
}
