package com.marketsvrn.network.datasource

import com.marketsvrn.common.Resource
import com.marketsvrn.network.model.dto.PlaceDTO

interface PlacesDataSource {
    suspend fun getPlaces(
        page: Int,
        pageSize: Int,
        marketId: Int
    ): Resource<List<PlaceDTO>>

    suspend fun getPlaceById(
        id: Int
    ): Resource<PlaceDTO>
}