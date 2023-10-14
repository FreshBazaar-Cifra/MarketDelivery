package com.marketsvrn.datastore.tokenstorage

class FakeTokenStorage: TokenStorage {
    override suspend fun getToken(): String {
        return "123"
    }

    override suspend fun writeToken(token: String?) {
    }
}