package com.marketsvrn.orders.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Order
import kotlinx.coroutines.flow.StateFlow
import java.util.SortedMap

interface OrdersComponent {
    fun refreshOrders()
    fun selectOrder(id: Int)

    val orders: StateFlow<Resource<SortedMap<String, MutableList<Order>>>>
}