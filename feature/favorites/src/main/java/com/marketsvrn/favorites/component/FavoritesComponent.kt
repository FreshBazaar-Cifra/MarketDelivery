package com.marketsvrn.favorites.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Product
import kotlinx.coroutines.flow.StateFlow

interface FavoritesComponent {
    fun refreshFavorites()
    fun selectFavorite(id: Int)
    fun removeFavorite(id: Int)

    val favorites: StateFlow<Resource<List<Product>>>
}