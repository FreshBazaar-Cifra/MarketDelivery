package com.marketsvrn.network.retrofit

import com.marketsvrn.common.Resource
import com.marketsvrn.network.api.ProductsApi
import com.marketsvrn.network.datasource.ProductsDataSource
import com.marketsvrn.network.model.dto.ProductDTO
import com.marketsvrn.network.util.BaseDataSource

class RetrofitProductsDataSource(
    private val api: ProductsApi
) : ProductsDataSource, BaseDataSource() {
    override suspend fun getProducts(
        page: Int,
        pageSize: Int,
        placeId: Int
    ): Resource<List<ProductDTO>> = getResult {
        api.getProducts(placeId, page, pageSize)
    }

    override suspend fun getProductById(
        id: Int
    ): Resource<ProductDTO> = getResult {
        api.getProductById(id)
    }

}