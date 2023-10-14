package com.marketsvrn.placeselect.component

import androidx.paging.PagingData
import com.marketsvrn.common.Resource
import com.marketsvrn.model.Market
import com.marketsvrn.placeselect.model.PlaceUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakePlaceSelectComponent : PlaceSelectComponent {
    override val market: StateFlow<Resource<Market>>
        get() = MutableStateFlow(Resource.success(Market.getStub()))
    override val placesPager: Flow<PagingData<PlaceUi>>
        get() = MutableStateFlow(PagingData.empty())

    override fun selectPlace(id: Int) {
    }

    override fun retryMarketLoading() {
    }
}
