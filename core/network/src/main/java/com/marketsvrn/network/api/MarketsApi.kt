package com.marketsvrn.network.api

import com.marketsvrn.network.model.dto.MarketDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketsApi {
    @Headers("Accept: application/json")
    @GET("market/all")
    suspend fun getMarkets(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int
    ): Response<List<MarketDTO>>

    @Headers("Accept: application/json")
    @GET("market/id/{market_id}")
    suspend fun getMarketById(
        @Path("market_id") marketId: Int
    ): Response<MarketDTO>
}