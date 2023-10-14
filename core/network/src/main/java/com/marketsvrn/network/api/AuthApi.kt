package com.marketsvrn.network.api

import com.marketsvrn.network.model.requests.LoginRequest
import com.marketsvrn.network.model.requests.RegisterRequest
import com.marketsvrn.network.model.responses.LoginResponse
import com.marketsvrn.network.model.responses.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("user/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("user/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>
}