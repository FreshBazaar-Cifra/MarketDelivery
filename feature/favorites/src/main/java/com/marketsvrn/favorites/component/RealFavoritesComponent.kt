package com.marketsvrn.favorites.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnResume
import com.marketsvrn.common.Resource
import com.marketsvrn.data.repository.favorites.FavoritesRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.inject

class RealFavoritesComponent(
    componentContext: ComponentContext
): FavoritesComponent, BaseComponent(componentContext) {
    private val repo: FavoritesRepository by inject()
    private var _favorites: MutableStateFlow<Resource<List<Product>>> =
        MutableStateFlow(Resource.notLoading())
    override val favorites: StateFlow<Resource<List<Product>>>
        get() = _favorites
    override fun refreshFavorites() {
        repo.getFavorites()
            .onStart {
                _favorites.value = Resource.loading()
            }
            .onEach {
                _favorites.value = it
            }
            .catch { ex ->
                ex.localizedMessage?.let { _favorites.value = Resource.error(ex.message ?: "No error message") }
            }
            .launchIn(ioScope)
    }

    override fun selectFavorite(id: Int) {
        TODO("Not yet implemented")
    }


    init {
        lifecycle.doOnResume {
            refreshFavorites()
        }
    }
}