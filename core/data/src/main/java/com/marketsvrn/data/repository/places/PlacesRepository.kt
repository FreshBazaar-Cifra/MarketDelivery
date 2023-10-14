package com.marketsvrn.data.repository.places

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Place
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun getPlaces(
        page: Int,
        pageSize: Int,
        marketId: Int
    ): Resource<List<Place>>

    fun getPlaceById(
        id: Int
    ): Flow<Resource<Place>>
}