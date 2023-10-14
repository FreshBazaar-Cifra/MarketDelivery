package com.marketsvrn.orderdetails.component

import com.arkivanov.decompose.ComponentContext
import com.marketsvrn.common.Resource
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealOrderDetailsComponent(
    componentContext: ComponentContext,
    private val orderId: Int
): OrderDetailsComponent, BaseComponent(componentContext) {
    override val order: StateFlow<Resource<Order>>
        get() = MutableStateFlow(Resource.success(Order.getStub()))

    override fun selectProduct(id: Int) {
    }
}