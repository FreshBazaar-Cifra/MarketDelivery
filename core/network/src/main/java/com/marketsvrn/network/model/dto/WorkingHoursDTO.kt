package com.marketsvrn.network.model.dto

import com.marketsvrn.model.WorkingHours
import kotlinx.serialization.Serializable

@Serializable
data class WorkingHoursDTO(
    val dayOfWeek: Int,
    val openingTime: String,
    val closingTime: String,
) {
    fun asDomainModel(): WorkingHours {
        return with(this){
            WorkingHours(dayOfWeek, openingTime, closingTime)
        }
    }
}