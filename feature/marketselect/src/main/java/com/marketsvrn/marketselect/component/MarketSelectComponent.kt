package com.marketsvrn.marketselect.component

import androidx.paging.PagingData
import com.marketsvrn.marketselect.model.MarketUi
import kotlinx.coroutines.flow.Flow

interface MarketSelectComponent {
    val marketsPager: Flow<PagingData<MarketUi>>

    fun selectMarket(id: Int)
}