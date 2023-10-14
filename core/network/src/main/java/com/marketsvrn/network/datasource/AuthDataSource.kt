package com.marketsvrn.network.datasource

import com.marketsvrn.common.Resource
import com.marketsvrn.network.model.requests.LoginRequest
import com.marketsvrn.network.model.requests.RegisterRequest
import com.marketsvrn.network.model.responses.LoginResponse
import com.marketsvrn.network.model.responses.RegisterResponse

interface AuthDataSource {
    suspend fun postLogin(
        request: LoginRequest
    ): Resource<LoginResponse>
    suspend fun postRegister(
        request: RegisterRequest
    ): Resource<RegisterResponse>
}