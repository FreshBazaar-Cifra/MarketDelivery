package com.marketsvrn.profile.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val profileModule = module {
    includes(dataModule)
}