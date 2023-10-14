package com.marketsvrn.network.retrofit

import com.marketsvrn.common.Resource
import com.marketsvrn.network.api.UserApi
import com.marketsvrn.network.datasource.UserDataSource
import com.marketsvrn.network.model.dto.UserDTO
import com.marketsvrn.network.util.BaseDataSource

class RetrofitUserDataSource(
    private val api: UserApi
) : UserDataSource, BaseDataSource() {
    override suspend fun getUser(): Resource<UserDTO> = getResult {
        api.getUser()
    }
}