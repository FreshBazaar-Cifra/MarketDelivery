package com.marketsvrn.delivery.di

import com.marketsvrn.basket.di.basketModule
import com.marketsvrn.buyorder.di.buyOrderModule
import com.marketsvrn.common.SplashScreenSingleton
import com.marketsvrn.logincontinue.di.loginContinueModule
import com.marketsvrn.marketselect.di.marketSelectModule
import com.marketsvrn.onboarding.di.onBoardingModule
import com.marketsvrn.orderdetails.di.orderDetailsModule
import com.marketsvrn.orders.di.ordersModule
import com.marketsvrn.placeselect.di.placeSelectModule
import com.marketsvrn.productdetails.di.productDetailsModule
import com.marketsvrn.productslist.di.productsListModule
import com.marketsvrn.profile.di.profileModule
import com.marketsvrn.profilesettings.di.profileSettingsModule
import org.koin.dsl.module

val appModule = module(createdAtStart = true) {
    includes(
        basketModule, buyOrderModule, loginContinueModule,
        marketSelectModule, onBoardingModule, orderDetailsModule,
        ordersModule, placeSelectModule, productDetailsModule, productsListModule,
        profileModule, profileSettingsModule
    )
    includes(imageModule)
    single(createdAtStart = true) {
        SplashScreenSingleton()
    }
}