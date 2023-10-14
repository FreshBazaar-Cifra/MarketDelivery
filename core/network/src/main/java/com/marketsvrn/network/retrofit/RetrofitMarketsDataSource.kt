package com.marketsvrn.network.retrofit

import com.marketsvrn.common.Resource
import com.marketsvrn.network.api.MarketsApi
import com.marketsvrn.network.datasource.MarketsDataSource
import com.marketsvrn.network.model.dto.MarketDTO
import com.marketsvrn.network.util.BaseDataSource

class RetrofitMarketsDataSource(
    private val api: MarketsApi
) : MarketsDataSource, BaseDataSource() {
    override suspend fun getMarkets(page: Int, pageSize: Int): Resource<List<MarketDTO>> {
        return getResult {
            api.getMarkets(page, pageSize)
        }
    }

    override suspend fun getMarketById(id: Int): Resource<MarketDTO> {
        return getResult {
            api.getMarketById(id)
        }
    }
}