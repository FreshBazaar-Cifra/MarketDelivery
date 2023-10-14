package com.marketsvrn.network.retrofit

import com.marketsvrn.common.Resource
import com.marketsvrn.network.api.OrdersApi
import com.marketsvrn.network.datasource.OrdersDataSource
import com.marketsvrn.network.model.dto.OrderDTO
import com.marketsvrn.network.util.BaseDataSource

class RetrofitOrdersDataSource(
    private val api: OrdersApi
) : OrdersDataSource, BaseDataSource() {
    override suspend fun getOrders(
    ): Resource<List<OrderDTO>> = getResult {
        api.getOrders()
    }

    override suspend fun getOrderById(
        id: Int
    ): Resource<OrderDTO> = getResult {
        api.getOrderById(id)
    }

}