package com.marketsvrn.productdetails.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val productDetailsModule = module {
    includes(dataModule)
}