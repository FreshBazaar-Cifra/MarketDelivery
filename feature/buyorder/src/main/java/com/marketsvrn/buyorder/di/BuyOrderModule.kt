package com.marketsvrn.buyorder.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val buyOrderModule = module {
    includes(dataModule)
}