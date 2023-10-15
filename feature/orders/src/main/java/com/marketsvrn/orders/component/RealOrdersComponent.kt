package com.marketsvrn.orders.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnResume
import com.marketsvrn.common.Resource
import com.marketsvrn.data.repository.orders.OrdersRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.Order
import com.marketsvrn.orders.util.OrderByDateMapper.Companion.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.inject
import java.util.SortedMap

class RealOrdersComponent(
    private val navigateToOrderDetails: (Int) -> Unit,
    componentContext: ComponentContext
) : OrdersComponent, BaseComponent(componentContext) {

    private val repo: OrdersRepository by inject()

    private var _orders: MutableStateFlow<Resource<SortedMap<String, MutableList<Order>>>> =
        MutableStateFlow(Resource.notLoading())
    override val orders: StateFlow<Resource<SortedMap<String, MutableList<Order>>>>
        get() = _orders

    override fun refreshOrders() {
        refreshOrdersFromRepo()
    }

    override fun selectOrder(id: Int) {
        navigateToOrderDetails(id)
    }

    private fun refreshOrdersFromRepo(){
        repo.getOrders()
            .map()
            .onStart {
                _orders.value = Resource.loading()
            }
            .onEach {
                _orders.value = it
            }
            .catch { ex ->
                ex.localizedMessage?.let { _orders.value = Resource.error(ex.message ?: "No error message") }
            }
            .launchIn(ioScope)
    }

    init {
        lifecycle.doOnResume {
            refreshOrders()
        }
    }
}