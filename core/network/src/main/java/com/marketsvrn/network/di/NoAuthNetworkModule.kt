package com.marketsvrn.network.di

import com.marketsvrn.network.api.AuthApi
import com.marketsvrn.network.datasource.AuthDataSource
import com.marketsvrn.network.retrofit.RetrofitAuthDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val noAuthNetworkModule = module {
    single<AuthDataSource> {
        RetrofitAuthDataSource(get())
    }

    single<AuthApi> {
        get<Retrofit>(named("noAuthRetrofit")).create(AuthApi::class.java)
    }

    single<OkHttpClient>(named("noAuthClient")) {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(get<HttpLoggingInterceptor>())
        builder.retryOnConnectionFailure(true)
        builder.followRedirects(true)
        builder.followSslRedirects(false)
        builder.build()
    }

    single<Retrofit>(named("noAuthRetrofit")) {
        Retrofit.Builder()
            .baseUrl(get<Endpoint>(named("authEndpoint")).url)
            .addConverterFactory(get())
            .client(get<OkHttpClient>(named("noAuthClient")))
            .build()
    }
}