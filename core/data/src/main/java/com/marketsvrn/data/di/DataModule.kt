package com.marketsvrn.data.di

import com.marketsvrn.data.repository.favorites.FavoritesRepository
import com.marketsvrn.data.repository.favorites.RealFavoritesRepository
import com.marketsvrn.data.repository.markets.MarketsRepository
import com.marketsvrn.data.repository.markets.RealMarketsRepository
import com.marketsvrn.data.repository.orders.OrdersRepository
import com.marketsvrn.data.repository.orders.RealOrdersRepository
import com.marketsvrn.data.repository.places.PlacesRepository
import com.marketsvrn.data.repository.places.RealPlacesRepository
import com.marketsvrn.data.repository.products.ProductsRepository
import com.marketsvrn.data.repository.products.RealProductsRepository
import com.marketsvrn.data.repository.user.RealUserRepository
import com.marketsvrn.data.repository.user.UserRepository
import com.marketsvrn.datastore.di.datastoreModule
import com.marketsvrn.network.di.networkModule
import org.koin.dsl.module

val dataModule = module(createdAtStart = true) {
    includes(networkModule, datastoreModule)

    single<MarketsRepository> {
        RealMarketsRepository(get())
    }

    single<PlacesRepository> {
        RealPlacesRepository(get())
    }

    single<OrdersRepository> {
        RealOrdersRepository(get())
    }

    single<UserRepository> {
        RealUserRepository(get(), get())
    }

    single<ProductsRepository> {
        RealProductsRepository(get())
    }

    single<FavoritesRepository> {
        RealFavoritesRepository(get())
    }
}