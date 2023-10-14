package com.marketsvrn.data.repository.markets

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Market
import kotlinx.coroutines.flow.Flow

interface MarketsRepository {
    suspend fun getMarkets(
        page: Int, pageSize: Int
    ): Resource<List<Market>>

    fun getMarketById(
        id: Int
    ): Flow<Resource<Market>>
}