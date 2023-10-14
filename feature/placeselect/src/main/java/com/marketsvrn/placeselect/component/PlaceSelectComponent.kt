package com.marketsvrn.placeselect.component

import androidx.paging.PagingData
import com.marketsvrn.common.Resource
import com.marketsvrn.model.Market
import com.marketsvrn.placeselect.model.PlaceUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PlaceSelectComponent {
    val market: StateFlow<Resource<Market>>

    val placesPager: Flow<PagingData<PlaceUi>>

    fun selectPlace(id: Int)

    fun retryMarketLoading()
}