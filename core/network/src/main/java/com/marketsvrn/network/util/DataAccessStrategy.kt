package com.marketsvrn.network.util

import com.marketsvrn.common.Resource
import com.marketsvrn.network.model.responses.LoginResponse
import com.marketsvrn.network.model.responses.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
object DataAccessStrategy {

}
fun <DTO, T> performNetworkOperation(
    call: suspend () -> Resource<DTO>,
    map: (DTO) -> T
): Flow<Resource<T>> {
    return flow {
        emit(Resource.loading())
        val networkCall = call.invoke()
        if (networkCall.status == Resource.Status.SUCCESS) {
            val data = map(networkCall.data!!)
            emit(Resource.success(data))
        } else if (networkCall.status == Resource.Status.ERROR) {
            emit(
                Resource.error(
                    "Error: ${networkCall.message}"
                )
            )
        }
    }
}

suspend fun <DTO, T> performPagingNetworkOperation(
    call: suspend () -> Resource<DTO>,
    map: (DTO) -> T
): Resource<T> {
    val networkCall = call.invoke()
    if (networkCall.status == Resource.Status.SUCCESS) {
        val data = map(networkCall.data!!)
        return Resource.success(data)
    } else if (networkCall.status == Resource.Status.ERROR) {
        return Resource.error(
            "Error: ${networkCall.message}"
        )
    }
    return Resource.error(
        "Error: empty"
    )
}

fun <DTO, T> performAuthTokenNetworkOperation(
    call: suspend () -> Resource<DTO>,
    map: (DTO) -> T,
    saveToken: (token: String) -> Unit,
): Flow<Resource<T>> {
    return flow {
        emit(Resource.loading())
        val networkCall = call.invoke()
        if (networkCall.status == Resource.Status.SUCCESS) {
            val data = map(networkCall.data!!)

            if (data is LoginResponse) {
                saveToken(data.token)
            }
            if (data is RegisterResponse) {
                saveToken(data.token)
            }
            emit(Resource.success(data))
        } else if (networkCall.status == Resource.Status.ERROR) {
            emit(
                Resource.error(
                    "Error: ${networkCall.message}"
                )
            )
        }
    }
}