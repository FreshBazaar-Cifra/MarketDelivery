package com.marketsvrn.network.retrofit

import com.marketsvrn.common.Resource
import com.marketsvrn.network.api.PlacesApi
import com.marketsvrn.network.datasource.PlacesDataSource
import com.marketsvrn.network.model.dto.PlaceDTO
import com.marketsvrn.network.util.BaseDataSource

class RetrofitPlacesDataSource(
    private val api: PlacesApi
) : PlacesDataSource, BaseDataSource() {
    override suspend fun getPlaces(
        page: Int,
        pageSize: Int,
        marketId: Int
    ): Resource<List<PlaceDTO>> = getResult {
        api.getPlaces(marketId, page, pageSize)
    }

    override suspend fun getPlaceById(
        id: Int
    ): Resource<PlaceDTO> = getResult {
        api.getPlaceById(id)
    }
}