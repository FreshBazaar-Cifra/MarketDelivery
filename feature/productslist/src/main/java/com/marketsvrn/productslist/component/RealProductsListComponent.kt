package com.marketsvrn.productslist.component

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.arkivanov.decompose.ComponentContext
import com.marketsvrn.data.paging.BasePagingSource
import com.marketsvrn.data.repository.products.ProductsRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.productslist.util.ProductToProductUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.inject

class RealProductsListComponent(
    componentContext: ComponentContext,
    private val onProductSelect: (Int) -> Unit,
    private val placeId: Int
): ProductsListComponent, BaseComponent(componentContext) {
    private val _selectedSortIndex = MutableStateFlow(0)
    override val selectedSortIndex: StateFlow<Int>
        get() = _selectedSortIndex
    private val _searchQuery = MutableStateFlow("")
    override val searchQuery: StateFlow<String>
        get() = _searchQuery


    private val productsRepository: ProductsRepository by inject()
    override val productsPager = Pager(PagingConfig(pageSize = 10)) {
        BasePagingSource(
            pageSize = 10,
            mapper = ProductToProductUiMapper(),
            inputReceiver = { page, pageSize ->
                productsRepository.getProducts(page, pageSize, placeId)
            }
        )
    }.flow.cachedIn(ioScope)

    override fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    override fun selectProduct(id: Int) {
        onProductSelect(id)
    }

    override fun selectSortBy(type: Int) {
        _selectedSortIndex.value = type
    }

    override fun search() {

    }
}