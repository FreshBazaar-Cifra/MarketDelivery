package com.marketsvrn.network.datasource

import com.marketsvrn.common.Resource
import com.marketsvrn.network.model.dto.MarketDTO

interface MarketsDataSource {
    suspend fun getMarkets(
        page: Int,
        pageSize: Int
    ): Resource<List<MarketDTO>>

    suspend fun getMarketById(
        id: Int
    ): Resource<MarketDTO>
}