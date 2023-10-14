package com.marketsvrn.data.repository.favorites

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Product
import com.marketsvrn.network.datasource.FavoriteDataSource
import com.marketsvrn.network.util.performNetworkOperation
import kotlinx.coroutines.flow.Flow

class RealFavoritesRepository(
    private val remoteDataSource: FavoriteDataSource
): FavoritesRepository {
    override fun getFavorites(): Flow<Resource<List<Product>>> {
        return performNetworkOperation(call = {
            remoteDataSource.getFavorites()
        }) { list ->
            list.map {
                it.asDomainModel()
            }
        }
    }

    override fun removeFavorite(id: Int): Flow<Resource<String>> {
        return performNetworkOperation(call = {
            remoteDataSource.deleteFavorite(id)
        }) {
            it
        }
    }

    override fun addFavorite(id: Int): Flow<Resource<String>> {
        return performNetworkOperation(call = {
            remoteDataSource.addFavorite(id)
        }) {
            it
        }
    }
}