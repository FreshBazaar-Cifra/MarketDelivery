package com.marketsvrn.network.fake

import com.marketsvrn.common.Resource
import com.marketsvrn.network.datasource.OrdersDataSource
import com.marketsvrn.network.model.dto.OrderDTO
import com.marketsvrn.network.util.BaseDataSource
import retrofit2.Response

class FakeOrdersDataSource: OrdersDataSource, BaseDataSource() {
    override suspend fun getOrders(): Resource<List<OrderDTO>> {
        return getResult {
            Response.success(List(10) { FakeModels.FAKE_ORDER_DTO.copy(id = it) })
        }
    }

    override suspend fun getOrderById(
        id: Int
    ): Resource<OrderDTO> {
        return getResult {
            Response.success(FakeModels.FAKE_ORDER_DTO.copy(id = id))
        }
    }
}