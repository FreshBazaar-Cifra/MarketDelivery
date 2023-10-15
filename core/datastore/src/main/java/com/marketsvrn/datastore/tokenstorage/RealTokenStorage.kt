package com.marketsvrn.datastore.tokenstorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.marketsvrn.datastore.prefs.PreferencesKeys.JWT_TOKEN
import kotlinx.coroutines.flow.first


class RealTokenStorage(
    private val dataStore: DataStore<Preferences>,
) : TokenStorage {
    override suspend fun getToken(): String? {
        val preference = dataStore.data.first()
        val token = preference[JWT_TOKEN]
        return if (!token.isNullOrEmpty()) token else null
    }

    override suspend fun writeToken(token: String?) {
        dataStore.edit {
            it[JWT_TOKEN] = token ?: ""
        }
    }
}