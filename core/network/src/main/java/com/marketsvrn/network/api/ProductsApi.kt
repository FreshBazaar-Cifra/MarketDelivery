package com.marketsvrn.network.api

import com.marketsvrn.network.model.dto.ProductDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ProductsApi {
    @Headers("Accept: application/json")
    @GET("product/all")
    suspend fun getProducts(
        @Query("place_id") placeId: Int,
        @Query("page") page: Int,
        @Query("limit") pageSize: Int
    ): Response<List<ProductDTO>>

    @Headers("Accept: application/json")
    @GET("product/id/{product_id}")
    suspend fun getProductById(
        @Query("product_id") productId: Int
    ): Response<ProductDTO>
}