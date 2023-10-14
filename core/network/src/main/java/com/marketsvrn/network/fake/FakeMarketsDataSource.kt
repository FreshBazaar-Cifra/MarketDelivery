package com.marketsvrn.network.fake

import com.marketsvrn.common.Resource
import com.marketsvrn.network.datasource.MarketsDataSource
import com.marketsvrn.network.model.dto.MarketDTO
import com.marketsvrn.network.util.BaseDataSource
import retrofit2.Response

class FakeMarketsDataSource: MarketsDataSource, BaseDataSource() {
    override suspend fun getMarkets(
        page: Int,
        pageSize: Int
    ): Resource<List<MarketDTO>> {
        return getResult {
            Response.success(List(10) {
                FakeModels.FAKE_MARKET_DTO.copy(id = getOffsetForPaging(page, pageSize, it))
            })
        }
    }

    override suspend fun getMarketById(
        id: Int
    ): Resource<MarketDTO> {
        return getResult {
            Response.success(FakeModels.FAKE_MARKET_DTO.copy(id = id))
        }
    }
}