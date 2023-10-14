package com.marketsvrn.network.model.dto

import com.marketsvrn.model.Address
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class AddressDTO(
    val city: String,
    val district: String?,
    val street: String,
    val home: String,
    val entrance: String?,
    val apartment: String?,
    @Transient val floor: String? = null,
    val intercom: String?,
    val latitude: Float,
    val longitude: Float
) {
    fun asDomainModel(): Address {
        return Address(city, district, street, home, entrance, apartment, floor, intercom, latitude, longitude)
    }
}
