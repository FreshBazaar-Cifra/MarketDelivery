package com.marketsvrn.logincontinue.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val loginContinueModule = module {
    includes(dataModule)
}