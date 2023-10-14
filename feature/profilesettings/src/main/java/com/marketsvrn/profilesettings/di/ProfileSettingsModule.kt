package com.marketsvrn.profilesettings.di

import com.marketsvrn.data.di.dataModule
import org.koin.dsl.module

val profileSettingsModule = module {
    includes(dataModule)
}