package com.marketsvrn.common

abstract class AbstractListResourceMapper<I : Any, O : Any>(
    private val mapListItem: (I) -> O
) : ListResourceMapper<I, O>, AbstractResourceMapper<List<I>, List<O>>(
    map = { list ->
        list.map {
            mapListItem(it)
        }
    }
)