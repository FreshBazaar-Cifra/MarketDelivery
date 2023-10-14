package com.marketsvrn.data.repository.markets

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Market
import com.marketsvrn.network.datasource.MarketsDataSource
import com.marketsvrn.network.util.performNetworkOperation
import com.marketsvrn.network.util.performPagingNetworkOperation
import kotlinx.coroutines.flow.Flow


class RealMarketsRepository(
    private val remoteDataSource: MarketsDataSource
): MarketsRepository {
    override suspend fun getMarkets(
        page: Int, pageSize: Int
    ): Resource<List<Market>> {
        return performPagingNetworkOperation(call = {
            remoteDataSource.getMarkets(page, pageSize)
        }) { list ->
            list.map {
                it.asDomainModel()
            }
        }
    }

    override fun getMarketById(
        id: Int
    ): Flow<Resource<Market>> {
        return performNetworkOperation(call = {
            remoteDataSource.getMarketById(id)
        }) {
            it.asDomainModel()
        }
    }
}