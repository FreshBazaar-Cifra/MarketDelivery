package com.marketsvrn.orderdetails.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeOrderDetailsComponent: OrderDetailsComponent {
    override val order: StateFlow<Resource<Order>>
        get() = MutableStateFlow(Resource.success(Order.getStub()))

    override fun selectProduct(id: Int) {
    }

    override fun refreshOrders() {
    }
}