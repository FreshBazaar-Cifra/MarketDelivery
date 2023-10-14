package com.marketsvrn.network.datasource

import com.marketsvrn.common.Resource
import com.marketsvrn.network.model.dto.UserDTO

interface UserDataSource {
    suspend fun getUser(): Resource<UserDTO>
}