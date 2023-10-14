package com.marketsvrn.onboarding.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val onBoardingModule = module {
    includes(dataModule)
}