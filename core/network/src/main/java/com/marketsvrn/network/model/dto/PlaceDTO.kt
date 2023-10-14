package com.marketsvrn.network.model.dto

import com.marketsvrn.model.Place
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDTO(
    val id: Int,
    val name: String,
    val logo: String,
    val description: String,
    val locationPhoto: String,
    val phones: List<String>,
    val estimate: Float,
    val market: MarketDTO,
    val workingHours: List<WorkingHoursDTO>
) {
    fun asDomainModel(): Place {
        return Place(
            id = id,
            logo = logo,
            name = name,
            description = description,
            locationPhoto = locationPhoto,
            phones = phones,
            market = market.asDomainModel(),
            workingHours = workingHours.map {
                it.asDomainModel()
            }
        )
    }
}
