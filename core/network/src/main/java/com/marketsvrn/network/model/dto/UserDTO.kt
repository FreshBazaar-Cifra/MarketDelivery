package com.marketsvrn.network.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phone: String?,
    val email: String?,
    val login: String
)