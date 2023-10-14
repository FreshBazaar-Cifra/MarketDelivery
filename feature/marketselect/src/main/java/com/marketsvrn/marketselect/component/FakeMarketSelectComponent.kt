package com.marketsvrn.marketselect.component

import androidx.paging.PagingData
import com.marketsvrn.marketselect.model.MarketUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMarketSelectComponent: MarketSelectComponent {
    override val marketsPager: Flow<PagingData<MarketUi>>
        get() = MutableStateFlow(
            PagingData.empty()
        )

    override fun selectMarket(id: Int) {
    }
}