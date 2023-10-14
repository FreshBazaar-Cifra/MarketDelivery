package com.marketsvrn.datastore.tokenstorage

interface TokenStorage {
    suspend fun getToken(): String?
    suspend fun writeToken(token: String?)
}