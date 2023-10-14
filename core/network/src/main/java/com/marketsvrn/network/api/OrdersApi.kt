package com.marketsvrn.network.api

import com.marketsvrn.network.model.dto.OrderDTO
import com.marketsvrn.network.model.requests.CreateOrderRequest
import com.marketsvrn.network.model.responses.CreateOrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface OrdersApi {
    @Headers("Accept: application/json")
    @GET("order/all")
    suspend fun getOrders(): Response<List<OrderDTO>>

    @Headers("Accept: application/json")
    @GET("order/id/{order_id}")
    suspend fun getOrderById(
        @Path("order_id") orderId: Int
    ): Response<OrderDTO>

    @Headers("Accept: application/json")
    @GET("order/create")
    suspend fun createOrder(
        @Body request: CreateOrderRequest
    ): Response<CreateOrderResponse>
}