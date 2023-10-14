package com.marketsvrn.network.api

import com.marketsvrn.network.model.dto.PlaceDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesApi {
    @Headers("Accept: application/json")
    @GET("place/all")
    suspend fun getPlaces(
        @Query("market_id") marketId: Int,
        @Query("page") page: Int,
        @Query("limit") pageSize: Int
    ): Response<List<PlaceDTO>>

    @Headers("Accept: application/json")
    @GET("place/id/{place_id}")
    suspend fun getPlaceById(
        @Path("place_id") placeId: Int,
    ): Response<PlaceDTO>
}