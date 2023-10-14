package com.marketsvrn.network.retrofit

import com.marketsvrn.common.Resource
import com.marketsvrn.network.api.FavoriteApi
import com.marketsvrn.network.datasource.FavoriteDataSource
import com.marketsvrn.network.model.dto.ProductDTO
import com.marketsvrn.network.util.BaseDataSource

class RetrofitFavoriteDataSource(
    private val api: FavoriteApi
) : FavoriteDataSource, BaseDataSource() {
    override suspend fun getFavorites(): Resource<List<ProductDTO>> {
        return getResult {
            api.getFavorites()
        }
    }

    override suspend fun deleteFavorite(productId: Int): Resource<String> {
        return getResult {
            api.removeFromFavorites(productId)
        }
    }

    override suspend fun addFavorite(productId: Int): Resource<String> {
        return getResult {
            api.addToFavorites(productId)
        }
    }
}