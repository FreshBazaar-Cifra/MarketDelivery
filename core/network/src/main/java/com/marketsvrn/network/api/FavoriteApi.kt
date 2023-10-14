package com.marketsvrn.network.api

import com.marketsvrn.network.model.dto.ProductDTO
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface FavoriteApi {
    @Headers("Accept: application/json")
    @PUT("favorite/{product_id}")
    suspend fun addToFavorites(
        @Path("product_id") productId: Int
    ): Response<String>

    @Headers("Accept: application/json")
    @DELETE("favorite/{product_id}")
    suspend fun removeFromFavorites(
        @Path("product_id") productId: Int
    ): Response<String>

    @Headers("Accept: application/json")
    @GET("favorite/")
    suspend fun getFavorites(): Response<List<ProductDTO>>
}