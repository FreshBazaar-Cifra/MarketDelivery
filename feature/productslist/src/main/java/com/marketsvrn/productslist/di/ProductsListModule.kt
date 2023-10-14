package com.marketsvrn.productslist.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val productsListModule = module {
    includes(dataModule)
}