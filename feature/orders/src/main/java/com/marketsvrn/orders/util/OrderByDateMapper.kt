package com.marketsvrn.orders.util

import com.marketsvrn.common.Resource
import com.marketsvrn.common.Resource.Status.ERROR
import com.marketsvrn.common.Resource.Status.LOADING
import com.marketsvrn.common.Resource.Status.NOT_LOADING
import com.marketsvrn.common.Resource.Status.SUCCESS
import com.marketsvrn.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.format.TextStyle
import java.util.Locale
import java.util.SortedMap

class OrderByDateMapper {
    companion object {
        private fun <I, O> convertResource(input: Resource<I>, map: (I) -> O): Resource<O> {
            return with (input) {
                when (status) {
                    SUCCESS -> Resource(status, map(data!!), message, exception)
                    ERROR -> Resource.error(message!!, map(data!!))
                    LOADING -> Resource.loading()
                    NOT_LOADING -> Resource.notLoading()
                }
            }
        }

        fun Flow<Resource<List<Order>>>.map(): Flow<Resource<SortedMap<String, MutableList<Order>>>> {
            return flow {
                collect { resource ->
                    if (resource.status == ERROR) {
                        emit(Resource.error(resource.message!!))
                    }
                    if (resource.status == LOADING) {
                        emit(Resource.loading())
                    }
                    if (resource.status == NOT_LOADING) {
                        emit(Resource.notLoading())
                    }
                    val newResource = convertResource(resource) { orders ->
                        val map = mutableMapOf<String, MutableList<Order>>()
                        orders.forEach {
                            val orderDateString = with(it.date) {
                                "${
                                    month.getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru"))
                                        .uppercase()
                                } $year"
                            }
                            if (map[orderDateString] == null) {
                                map[orderDateString] = mutableListOf(it)
                            } else {
                                map[orderDateString]!! += it
                            }
                        }
                        val sortedMap = map.toSortedMap(DateStringComparator::compare)
                        map.forEach { entry ->
                            entry.value.sortByDescending {
                                it.date
                            }
                        }
                        sortedMap
                    }
                    emit(newResource)
                }

            }

        }
    }
}