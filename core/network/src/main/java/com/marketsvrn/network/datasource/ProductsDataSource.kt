package com.marketsvrn.network.datasource

import com.marketsvrn.common.Resource
import com.marketsvrn.network.model.dto.ProductDTO

interface ProductsDataSource {
    suspend fun getProducts(
        page: Int,
        pageSize: Int,
        placeId: Int
    ): Resource<List<ProductDTO>>

    suspend fun getProductById(
        id: Int
    ): Resource<ProductDTO>
}