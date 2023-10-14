package com.marketsvrn.productslist.component

import androidx.paging.PagingData
import com.marketsvrn.model.ProductCategory
import com.marketsvrn.productslist.model.ProductUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ProductsListComponent {
    val categories: StateFlow<List<ProductCategory>>
        get() = MutableStateFlow(
            listOf(
                ProductCategory(0, "Мясо"),
                ProductCategory(1, "Молочные продукты"),
                ProductCategory(2, "Минеральная вода"),
            )
        )
    val selectedSortIndex: StateFlow<Int>
    val searchQuery: StateFlow<String>
    val productsPager: Flow<PagingData<ProductUi>>
    fun onSearchQueryChange(newQuery: String)
    fun selectProduct(id: Int)
    fun selectSortBy(type: Int)
    fun search()
}