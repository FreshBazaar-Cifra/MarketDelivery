package com.marketsvrn.orderdetails.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnResume
import com.marketsvrn.common.Resource
import com.marketsvrn.data.repository.orders.OrdersRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.inject

class RealOrderDetailsComponent(
    componentContext: ComponentContext,
    private val orderId: Int
): OrderDetailsComponent, BaseComponent(componentContext) {
    private val repo: OrdersRepository by inject()

    private val _order: MutableStateFlow<Resource<Order>> =
        MutableStateFlow(Resource.notLoading())
    override val order: StateFlow<Resource<Order>>
        get() = _order

    override fun selectProduct(id: Int) {
    }

    override fun refreshOrders() {
        repo.getOrderById(orderId)
            .onStart {
                _order.value = Resource.loading()
            }
            .onEach {
                _order.value = it
            }
            .catch { ex ->
                ex.localizedMessage?.let { _order.value = Resource.error(ex.message ?: "No error message") }
            }
            .launchIn(ioScope)
    }

    init {
        lifecycle.doOnResume {
            refreshOrders()
        }
    }
}