package com.marketsvrn.data.repository.places

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Place
import com.marketsvrn.network.datasource.PlacesDataSource
import com.marketsvrn.network.util.performNetworkOperation
import com.marketsvrn.network.util.performPagingNetworkOperation
import kotlinx.coroutines.flow.Flow

class RealPlacesRepository(
    private val remoteDataSource: PlacesDataSource
): PlacesRepository {
    override suspend fun getPlaces(page: Int, pageSize: Int, marketId: Int): Resource<List<Place>> {
        return performPagingNetworkOperation(
            call = {
                remoteDataSource.getPlaces(page, pageSize, marketId)
            }
        ) { list ->
            list.map {
                it.asDomainModel()
            }
        }
    }

    override fun getPlaceById(id: Int): Flow<Resource<Place>> {
        return performNetworkOperation(call = {
            remoteDataSource.getPlaceById(id)
        }) {
            it.asDomainModel()
        }
    }
}