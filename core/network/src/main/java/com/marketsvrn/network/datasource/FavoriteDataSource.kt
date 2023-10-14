package com.marketsvrn.network.datasource

import com.marketsvrn.common.Resource
import com.marketsvrn.network.model.dto.ProductDTO

interface FavoriteDataSource {
    suspend fun getFavorites(): Resource<List<ProductDTO>>
    suspend fun deleteFavorite(productId: Int): Resource<String>
    suspend fun addFavorite(productId: Int): Resource<String>
}