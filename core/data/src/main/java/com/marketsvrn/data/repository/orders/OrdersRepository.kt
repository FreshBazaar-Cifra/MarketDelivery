package com.marketsvrn.data.repository.orders

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Order
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {
    fun getOrders(): Flow<Resource<List<Order>>>
    fun getOrderById(id: Int): Flow<Resource<Order>>
}