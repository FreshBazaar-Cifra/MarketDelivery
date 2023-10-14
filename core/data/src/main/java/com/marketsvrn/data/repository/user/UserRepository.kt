package com.marketsvrn.data.repository.user

import com.marketsvrn.common.Resource
import com.marketsvrn.model.LoginData
import com.marketsvrn.model.RegisterData
import com.marketsvrn.network.model.responses.LoginResponse
import com.marketsvrn.network.model.responses.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(
        loginRequest: LoginData
    ): Flow<Resource<LoginResponse>>

    fun register(
        registerRequest: RegisterData
    ): Flow<Resource<RegisterResponse>>

    fun logOut()

    fun hasLogin(): Boolean
}