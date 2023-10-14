package com.marketsvrn.network.di

import org.koin.dsl.module

val networkModule = module {
    includes(baseNetworkModule, authNetworkModule, noAuthNetworkModule)
}