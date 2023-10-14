package com.marketsvrn.data.repository.user

import com.marketsvrn.common.Resource
import com.marketsvrn.datastore.tokenstorage.TokenStorage
import com.marketsvrn.model.LoginData
import com.marketsvrn.model.RegisterData
import com.marketsvrn.network.datasource.AuthDataSource
import com.marketsvrn.network.model.requests.LoginRequest
import com.marketsvrn.network.model.requests.RegisterRequest
import com.marketsvrn.network.model.responses.LoginResponse
import com.marketsvrn.network.model.responses.RegisterResponse
import com.marketsvrn.network.util.performAuthTokenNetworkOperation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class RealUserRepository(
    private val dataSource: AuthDataSource,
    private val tokenStorage: TokenStorage
) : UserRepository {
    override fun login(loginRequest: LoginData): Flow<Resource<LoginResponse>> {
        return performAuthTokenNetworkOperation(
            call = {
                dataSource.postLogin(LoginRequest.fromDomainModel(loginRequest))
            },
            map = { it },
            saveToken = { token: String ->
                runBlocking {
                    tokenStorage.writeToken(token)
                }
            }
        )
    }

    override fun register(
        registerRequest: RegisterData
    ): Flow<Resource<RegisterResponse>> {
        return performAuthTokenNetworkOperation(
            call = {
                dataSource.postRegister(RegisterRequest.fromDomainModel(registerRequest))
            },
            map = { it },
            saveToken = { token: String ->
                runBlocking {
                    tokenStorage.writeToken(token)
                }
            }
        )
    }

    override fun logOut() {
        runBlocking {
            tokenStorage.writeToken(null)
        }
    }

    override fun hasLogin(): Boolean {
        return runBlocking {
            tokenStorage.getToken() != null
        }
    }
}