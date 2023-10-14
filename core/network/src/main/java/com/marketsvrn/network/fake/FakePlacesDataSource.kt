package com.marketsvrn.network.fake

import com.marketsvrn.common.Resource
import com.marketsvrn.network.datasource.PlacesDataSource
import com.marketsvrn.network.model.dto.PlaceDTO
import com.marketsvrn.network.util.BaseDataSource
import retrofit2.Response

class FakePlacesDataSource: PlacesDataSource, BaseDataSource() {
    override suspend fun getPlaces(
        page: Int,
        pageSize: Int,
        marketId: Int
    ): Resource<List<PlaceDTO>> {
        return getResult {
            Response.success(List(10) {
                FakeModels.FAKE_PLACE_DTO.copy(id = getOffsetForPaging(
                    page,
                    pageSize,
                    it
                ), market = FakeModels.FAKE_MARKET_DTO.copy(id = marketId))
            })
        }
    }

    override suspend fun getPlaceById(
        id: Int
    ): Resource<PlaceDTO> {
        return getResult {
            Response.success(
                FakeModels.FAKE_PLACE_DTO.copy(id = id)
            )
        }
    }
}