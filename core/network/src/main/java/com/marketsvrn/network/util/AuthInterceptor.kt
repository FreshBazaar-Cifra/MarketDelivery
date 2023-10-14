package com.marketsvrn.network.util

import com.marketsvrn.datastore.tokenstorage.TokenStorage
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStorage: TokenStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        var token: String?
        return runBlocking {
            token = tokenStorage.getToken()
            if (!token.isNullOrEmpty()) {
                val authorized = original.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(authorized)
            } else {
                chain.proceed(original)
            }
        }
    }
}