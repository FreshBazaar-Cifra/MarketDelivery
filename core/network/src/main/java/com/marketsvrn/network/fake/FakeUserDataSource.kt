package com.marketsvrn.network.fake

import com.marketsvrn.common.Resource
import com.marketsvrn.network.datasource.UserDataSource
import com.marketsvrn.network.model.dto.UserDTO
import com.marketsvrn.network.util.BaseDataSource
import retrofit2.Response

class FakeUserDataSource : UserDataSource, BaseDataSource() {
    override suspend fun getUser(): Resource<UserDTO> {
        return getResult {
            Response.success(FakeModels.FAKE_USER_DTO)
        }
    }
}