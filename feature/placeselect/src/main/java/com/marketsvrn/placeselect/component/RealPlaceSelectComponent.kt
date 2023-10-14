package com.marketsvrn.placeselect.component

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.marketsvrn.common.Resource
import com.marketsvrn.data.paging.BasePagingSource
import com.marketsvrn.data.repository.markets.MarketsRepository
import com.marketsvrn.data.repository.places.PlacesRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.model.Market
import com.marketsvrn.placeselect.util.PlaceToPlaceUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import org.koin.core.component.inject

class RealPlaceSelectComponent(
    componentContext: ComponentContext,
    val onPlaceSelect: (Int) -> Unit,
    val marketId: Int
): PlaceSelectComponent, BaseComponent(componentContext) {

    private val marketsRepository: MarketsRepository by inject()
    private var _market = MutableStateFlow<Resource<Market>>(Resource.notLoading(null))

    override val market: StateFlow<Resource<Market>>
        get() = _market
    private val placesRepository: PlacesRepository by inject()
    override val placesPager = Pager(PagingConfig(pageSize = 10)) {
        BasePagingSource(
            pageSize = 10,
            mapper = PlaceToPlaceUiMapper(),
            inputReceiver = { page, pageSize ->
                placesRepository.getPlaces(page, pageSize, marketId)
            }
        )
    }.flow.cachedIn(ioScope)

    override fun selectPlace(id: Int) {
        onPlaceSelect(id)
    }

    override fun retryMarketLoading() {
        marketsRepository.getMarketById(marketId)
            .onStart {
                _market.value = Resource.loading()
            }
            .onEach { market ->
                _market.value = market
            }
            .launchIn(ioScope)
    }

    init {
        lifecycle.doOnCreate {
            retryMarketLoading()
        }
    }
}