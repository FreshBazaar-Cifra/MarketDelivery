package com.marketsvrn.orders.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Order
import kotlinx.coroutines.flow.StateFlow
import java.util.SortedMap

class FakeOrdersComponent : OrdersComponent {
    override fun refreshOrders() {
    }

    override fun selectOrder(id: Int) {
    }

    override val orders: StateFlow<Resource<SortedMap<String, MutableList<Order>>>>
        get() = TODO()

}