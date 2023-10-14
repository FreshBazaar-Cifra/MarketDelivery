package com.marketsvrn.network.model.requests

import com.marketsvrn.model.RegisterData
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val login: String,
    val password: String
) {
    companion object {
        fun fromDomainModel(registerData: RegisterData): RegisterRequest {
            return with(registerData) {
                RegisterRequest(
                    login = login,
                    password = password,
                    firstName = firstName,
                    lastName = lastName
                )
            }
        }
    }
}