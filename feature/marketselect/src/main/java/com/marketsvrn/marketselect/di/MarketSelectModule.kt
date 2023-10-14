package com.marketsvrn.marketselect.di

import com.marketsvrn.data.di.dataModule
import com.marketsvrn.marketselect.util.MarketToMarketUiMapper
import org.koin.dsl.module

val marketSelectModule = module(createdAtStart = true) {
    includes(dataModule)

    single(createdAtStart = true) {
        MarketToMarketUiMapper()
    }
}