package com.marketsvrn.placeselect.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val placeSelectModule = module {
    includes(dataModule)
}