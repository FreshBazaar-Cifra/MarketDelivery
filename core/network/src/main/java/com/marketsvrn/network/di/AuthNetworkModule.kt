package com.marketsvrn.network.di

import com.marketsvrn.datastore.di.datastoreModule
import com.marketsvrn.datastore.tokenstorage.TokenStorage
import com.marketsvrn.network.api.FavoriteApi
import com.marketsvrn.network.api.MarketsApi
import com.marketsvrn.network.api.OrdersApi
import com.marketsvrn.network.api.PlacesApi
import com.marketsvrn.network.api.ProductsApi
import com.marketsvrn.network.api.PromoApi
import com.marketsvrn.network.api.UserApi
import com.marketsvrn.network.datasource.FavoriteDataSource
import com.marketsvrn.network.datasource.MarketsDataSource
import com.marketsvrn.network.datasource.OrdersDataSource
import com.marketsvrn.network.datasource.PlacesDataSource
import com.marketsvrn.network.datasource.ProductsDataSource
import com.marketsvrn.network.datasource.PromoDataSource
import com.marketsvrn.network.datasource.UserDataSource
import com.marketsvrn.network.retrofit.RetrofitFavoriteDataSource
import com.marketsvrn.network.retrofit.RetrofitMarketsDataSource
import com.marketsvrn.network.retrofit.RetrofitOrdersDataSource
import com.marketsvrn.network.retrofit.RetrofitPlacesDataSource
import com.marketsvrn.network.retrofit.RetrofitProductsDataSource
import com.marketsvrn.network.retrofit.RetrofitPromoDataSource
import com.marketsvrn.network.retrofit.RetrofitUserDataSource
import com.marketsvrn.network.util.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val authNetworkModule = module {
    includes(datastoreModule)

    single<FavoriteDataSource> {
        RetrofitFavoriteDataSource(
            api = get()
        )
    }

    single<MarketsDataSource> {
        RetrofitMarketsDataSource(
            api = get()
        )
    }

    single<OrdersDataSource> {
        RetrofitOrdersDataSource(
            api = get()
        )
    }

    single<PlacesDataSource> {
        RetrofitPlacesDataSource(
            api = get()
        )
    }

    single<ProductsDataSource> {
        RetrofitProductsDataSource(
            api = get()
        )
    }

    single<PromoDataSource> {
        RetrofitPromoDataSource(
            api = get()
        )
    }

    single<UserDataSource> {
        RetrofitUserDataSource(
            api = get()
        )
    }


    single<FavoriteApi> {
        get<Retrofit>(named("authRetrofit")).create(FavoriteApi::class.java)
    }
    single<MarketsApi> {
        get<Retrofit>(named("authRetrofit")).create(MarketsApi::class.java)
    }
    single<OrdersApi> {
        get<Retrofit>(named("authRetrofit")).create(OrdersApi::class.java)
    }
    single<PlacesApi> {
        get<Retrofit>(named("authRetrofit")).create(PlacesApi::class.java)
    }
    single<ProductsApi> {
        get<Retrofit>(named("authRetrofit")).create(ProductsApi::class.java)
    }
    single<PromoApi> {
        get<Retrofit>(named("authRetrofit")).create(PromoApi::class.java)
    }
    single<UserApi> {
        get<Retrofit>(named("authRetrofit")).create(UserApi::class.java)
    }

    single {
        AuthInterceptor(
            tokenStorage = get<TokenStorage>()
        )
    }

    single<OkHttpClient>(named("authClient")) {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(get<HttpLoggingInterceptor>())
        builder.retryOnConnectionFailure(true)
        builder.followRedirects(true)
        builder.followSslRedirects(false)
        builder.addInterceptor(get<AuthInterceptor>())
        builder.build()
    }

    single<Retrofit>(named("authRetrofit")) {
        Retrofit.Builder()
            .baseUrl(get<Endpoint>(named("mainEndpoint")).url)
            .addConverterFactory(get())
            .client(get<OkHttpClient>(named("authClient")))
            .build()
    }
}