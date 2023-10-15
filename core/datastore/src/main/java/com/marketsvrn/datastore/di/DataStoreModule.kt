package com.marketsvrn.datastore.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.marketsvrn.datastore.UserCredentialsSerializer
import com.marketsvrn.datastore.UserCredentialsSource
import com.marketsvrn.datastore.tokenstorage.RealTokenStorage
import com.marketsvrn.datastore.tokenstorage.TokenStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val datastoreModule = module {
    single<TokenStorage> {
        RealTokenStorage(get(named("prefsStore")))
        //FakeTokenStorage()
        /*if (FAKE_TOKEN) {
            FakeTokenStorage()

        } else {
            RealTokenStorage(
                dataStore = get(named("prefsStore"))
            )
        }*/

    }

    single<UserCredentialsSerializer> {
        UserCredentialsSerializer()
    }

    single<UserCredentialsSource> {
        UserCredentialsSource(get(named("userStore")))
    }

    single(named("prefsStore")) {
        PreferenceDataStoreFactory.create(
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        ) {
            androidContext().preferencesDataStoreFile("main_prefs.preferences_pb")
        }
    }

    single(named("userStore")) {
        DataStoreFactory.create(
            serializer = get<UserCredentialsSerializer>(),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            migrations = listOf()
        ) {
            androidContext().dataStoreFile("user_credentials.pb")
        }
    }
}