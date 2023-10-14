package com.marketsvrn.favorites.component

import com.marketsvrn.common.Resource
import com.marketsvrn.model.Product
import kotlinx.coroutines.flow.StateFlow

class FakeFavoritesComponent: FavoritesComponent {
    override fun refreshFavorites() {
        TODO("Not yet implemented")
    }

    override fun selectFavorite(id: Int) {
        TODO("Not yet implemented")
    }

    override fun removeFavorite(id: Int) {
        TODO("Not yet implemented")
    }

    override val favorites: StateFlow<Resource<List<Product>>>
        get() = TODO("Not yet implemented")
}