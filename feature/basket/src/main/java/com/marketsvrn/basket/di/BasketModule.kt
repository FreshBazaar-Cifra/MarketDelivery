package com.marketsvrn.basket.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val basketModule = module {
    includes(dataModule)
}