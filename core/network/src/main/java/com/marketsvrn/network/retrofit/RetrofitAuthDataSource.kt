package com.marketsvrn.network.retrofit

import com.marketsvrn.common.Resource
import com.marketsvrn.network.api.AuthApi
import com.marketsvrn.network.datasource.AuthDataSource
import com.marketsvrn.network.model.requests.LoginRequest
import com.marketsvrn.network.model.requests.RegisterRequest
import com.marketsvrn.network.model.responses.LoginResponse
import com.marketsvrn.network.model.responses.RegisterResponse
import com.marketsvrn.network.util.BaseDataSource

class RetrofitAuthDataSource(
    private val networkApi: AuthApi
) : AuthDataSource, BaseDataSource() {
    override suspend fun postLogin(
        request: LoginRequest
    ): Resource<LoginResponse> {
        return getResult {
            networkApi.login(request)
        }
    }

    override suspend fun postRegister(
        request: RegisterRequest
    ): Resource<RegisterResponse> {
        return getResult {
            networkApi.register(request)
        }
    }
}