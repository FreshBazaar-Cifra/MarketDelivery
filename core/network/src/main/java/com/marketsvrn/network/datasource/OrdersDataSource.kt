package com.marketsvrn.network.datasource

import com.marketsvrn.common.Resource
import com.marketsvrn.network.model.dto.OrderDTO

interface OrdersDataSource {
    suspend fun getOrders(): Resource<List<OrderDTO>>

    suspend fun getOrderById(
        id: Int
    ): Resource<OrderDTO>
}