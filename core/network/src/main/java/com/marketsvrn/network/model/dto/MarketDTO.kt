package com.marketsvrn.network.model.dto

import com.marketsvrn.model.Market
import kotlinx.serialization.Serializable

@Serializable
data class MarketDTO(
        val id: Int,
        val name: String,
        val images: List<String>,
        val address: AddressDTO,
        val workingHours: List<WorkingHoursDTO>,
) {
    fun asDomainModel(): Market = Market(id, name, images, address.asDomainModel(), workingHours.map { it.asDomainModel() })
}
