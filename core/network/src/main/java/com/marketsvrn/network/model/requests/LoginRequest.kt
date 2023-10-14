package com.marketsvrn.network.model.requests

import com.marketsvrn.model.LoginData
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val login: String,
    val password: String
) {
    companion object {
        fun fromDomainModel(loginData: LoginData): LoginRequest {
            return with(loginData) {
                LoginRequest(
                    login = login,
                    password = password
                )
            }
        }
    }
}
