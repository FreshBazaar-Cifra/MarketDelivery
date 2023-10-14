package com.marketsvrn.data.repository.favorites

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<Resource<List<Product>>>
    fun removeFavorite(id: Int): Flow<Resource<String>>
    fun addFavorite(id: Int): Flow<Resource<String>>
}