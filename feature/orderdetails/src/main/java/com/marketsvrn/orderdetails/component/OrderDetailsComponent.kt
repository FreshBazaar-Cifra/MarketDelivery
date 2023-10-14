package com.marketsvrn.orderdetails.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Order
import kotlinx.coroutines.flow.StateFlow

interface OrderDetailsComponent {
    val order: StateFlow<Resource<Order>>
    fun selectProduct(id: Int)
}