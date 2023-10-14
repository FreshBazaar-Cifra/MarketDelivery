package com.marketsvrn.network.util

import com.marketsvrn.common.Resource
import retrofit2.Response

abstract class BaseDataSource {
    protected fun getOffsetForPaging(page: Int, pageSize: Int, offset: Int): Int {
        return (page + 1) * pageSize + offset
    }
    protected suspend fun <T> getResult(
        call: suspend () -> Response<T>
    ): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            val errorBody = response.errorBody()?.string()
            return error("${response.code()} - $errorBody")
        } catch (err: Exception) {
            return error(
                err.message ?: "No error message.",
                exception = err
            )
        }
    }

    private fun <T> error(
        message: String,
        exception: Throwable? = null
    ): Resource<T> {
        return Resource.error(
            message = "Network error: $message.",
            exception = exception
        )
    }
}