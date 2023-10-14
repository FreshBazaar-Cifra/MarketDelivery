package com.marketsvrn.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class UserCredentialsSerializer : Serializer<UserCredentials> {
    override val defaultValue: UserCredentials = userCredentials {
        login = ""
        password = ""
        token = ""
        loggedIn = false
    }

    override suspend fun readFrom(input: InputStream): UserCredentials {
        try {
            // readFrom is already called on the data store background thread
            @Suppress("BlockingMethodInNonBlockingContext")
            return UserCredentials.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserCredentials, output: OutputStream) {
        // writeTo is already called on the data store background thread
        @Suppress("BlockingMethodInNonBlockingContext")
        t.writeTo(output)
    }
}