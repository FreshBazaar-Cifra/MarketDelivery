package com.marketsvrn.data.repository.orders

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Order
import com.marketsvrn.network.datasource.OrdersDataSource
import com.marketsvrn.network.util.performNetworkOperation
import kotlinx.coroutines.flow.Flow

class RealOrdersRepository(
    private val remoteDataSource: OrdersDataSource
): OrdersRepository {
    override fun getOrders(): Flow<Resource<List<Order>>> {
        return performNetworkOperation(call = {
            remoteDataSource.getOrders()
        }) { list ->
            list.map {
                it.asDomainModel()
            }
        }
    }

    override fun getOrderById(id: Int): Flow<Resource<Order>> {
        return performNetworkOperation(call = {
            remoteDataSource.getOrderById(id)
        }) {
            it.asDomainModel()
        }
    }
}