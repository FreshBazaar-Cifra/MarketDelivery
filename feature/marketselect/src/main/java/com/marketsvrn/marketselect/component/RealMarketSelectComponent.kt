package com.marketsvrn.marketselect.component

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.arkivanov.decompose.ComponentContext
import com.marketsvrn.data.paging.BasePagingSource
import com.marketsvrn.data.repository.markets.MarketsRepository
import com.marketsvrn.designsystem.util.BaseComponent
import com.marketsvrn.marketselect.util.MarketToMarketUiMapper
import org.koin.core.component.inject

class RealMarketSelectComponent(
    val onMarketSelect: (Int) -> Unit,
    componentContext: ComponentContext
) : MarketSelectComponent, BaseComponent(componentContext) {

    private val repo: MarketsRepository by inject()
    private val mapper: MarketToMarketUiMapper by inject()

    override val marketsPager = Pager(PagingConfig(pageSize = 10, initialLoadSize = 50)) {
        BasePagingSource(
            pageSize = 10,
            mapper = mapper,
            inputReceiver = { page, pageSize ->
                repo.getMarkets(page, pageSize)
            }
        )
    }.flow.cachedIn(ioScope)

    override fun selectMarket(id: Int) {
        onMarketSelect(id)
    }
}