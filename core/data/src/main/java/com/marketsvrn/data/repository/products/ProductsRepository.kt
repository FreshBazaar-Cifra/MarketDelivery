package com.marketsvrn.data.repository.products

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(
        page: Int,
        pageSize: Int,
        placeId: Int
    ): Resource<List<Product>>

    fun getProductById(
        id: Int
    ): Flow<Resource<Product>>
}