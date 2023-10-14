package com.marketsvrn.network.api

import com.marketsvrn.network.model.responses.PromoPriceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PromoApi {
    @Headers("Accept: application/json")
    @GET("promocode/price")
    suspend fun getPromocodePrice(
        @Query("code") code: String,
        @Query("price") price: Float,
    ): Response<List<PromoPriceResponse>>
}