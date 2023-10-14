package com.marketsvrn.common

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val exception: Throwable?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        NOT_LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(
            message: String,
            data: T? = null,
            exception: Throwable? = null
        ): Resource<T> {
            return Resource(Status.ERROR, data, message, exception)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }

        fun <T> notLoading(data: T? = null): Resource<T> {
            return Resource(Status.NOT_LOADING, data, null, null)
        }
    }
}