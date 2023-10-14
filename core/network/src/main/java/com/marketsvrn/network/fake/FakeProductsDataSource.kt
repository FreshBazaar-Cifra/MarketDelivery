package com.marketsvrn.network.fake

import com.marketsvrn.common.Resource
import com.marketsvrn.network.datasource.ProductsDataSource
import com.marketsvrn.network.model.dto.ProductDTO
import com.marketsvrn.network.util.BaseDataSource
import retrofit2.Response

class FakeProductsDataSource: ProductsDataSource, BaseDataSource() {
    override suspend fun getProducts(
        page: Int,
        pageSize: Int,
        placeId: Int
    ): Resource<List<ProductDTO>> {
        return getResult {
            Response.success(List(pageSize) {
                FakeModels.FAKE_PRODUCT_DTO.copy(
                    id = getOffsetForPaging(
                        page,
                        pageSize,
                        it
                    )
                )
            })
        }
    }

    override suspend fun getProductById(
        id: Int
    ): Resource<ProductDTO> {
        return getResult {
            Response.success(FakeModels.FAKE_PRODUCT_DTO.copy(id = id))
        }
    }
}