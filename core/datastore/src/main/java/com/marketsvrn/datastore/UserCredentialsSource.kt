package com.marketsvrn.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import java.io.IOException

class UserCredentialsSource(
    private val dataStore: DataStore<UserCredentials>
) {

    suspend fun setToken(newToken: String) {
        try {
            dataStore.updateData {
                it.copy {
                    token = newToken
                    loggedIn = true
                }
            }
        } catch (ioException: IOException) {
            Log.e("DataSource", "Failed to update user data", ioException)
        }
    }

    suspend fun logOut() {
        try {
            dataStore.updateData {
                userCredentials {
                    login = ""
                    password = ""
                    token = ""
                    loggedIn = false
                }
            }
        } catch (ioException: IOException) {
            Log.e("DataSource", "Failed to update user data", ioException)
        }
    }
}