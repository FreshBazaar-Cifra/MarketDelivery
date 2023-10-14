package com.marketsvrn.productslist.component

import androidx.paging.PagingData
import com.marketsvrn.productslist.model.ProductUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeProductsListComponent: ProductsListComponent {
    private val _selectedSortIndex = MutableStateFlow(0)
    override val selectedSortIndex: StateFlow<Int>
        get() = _selectedSortIndex
    private val _searchQuery = MutableStateFlow("")
    override val searchQuery: StateFlow<String>
        get() = _searchQuery
    override val productsPager: Flow<PagingData<ProductUi>> = MutableStateFlow(PagingData.empty())

    override fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    override fun selectProduct(id: Int) {
    }

    override fun selectSortBy(type: Int) {
        _selectedSortIndex.value = type
    }

    override fun search() {

    }
}