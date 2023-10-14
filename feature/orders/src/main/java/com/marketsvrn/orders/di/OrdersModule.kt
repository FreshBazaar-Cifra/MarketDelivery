package com.marketsvrn.orders.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val ordersModule = module {
    includes(dataModule)
}