package com.marketsvrn.network.api

import com.marketsvrn.network.model.dto.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApi {
    @Headers("Accept: application/json")
    @GET("user/")
    suspend fun getUser(): Response<UserDTO>
}