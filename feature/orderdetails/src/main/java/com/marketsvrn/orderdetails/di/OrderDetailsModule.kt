package com.marketsvrn.orderdetails.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val orderDetailsModule = module {
    includes(dataModule)
}