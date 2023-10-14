package com.marketsvrn.data.repository.products

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Product
import com.marketsvrn.network.datasource.ProductsDataSource
import com.marketsvrn.network.util.performNetworkOperation
import com.marketsvrn.network.util.performPagingNetworkOperation
import kotlinx.coroutines.flow.Flow

class RealProductsRepository(
    private val remoteDataSource: ProductsDataSource
): ProductsRepository {
    override suspend fun getProducts(
        page: Int,
        pageSize: Int,
        placeId: Int
    ): Resource<List<Product>> {
        return performPagingNetworkOperation(
            call = {
                remoteDataSource.getProducts(
                    page = page,
                    pageSize = pageSize,
                    placeId = placeId
                )
            }
        ) { list ->
            list.map {
                it.asDomainModel()
            }
        }
    }

    override fun getProductById(id: Int): Flow<Resource<Product>> {
        return performNetworkOperation(call = {
            remoteDataSource.getProductById(id)
        }) {
            it.asDomainModel()
        }
    }
}