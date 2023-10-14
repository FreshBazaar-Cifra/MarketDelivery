package com.marketsvrn.common

abstract class AbstractResourceMapper<I : Any, O : Any>(
    private val map: (I) -> O
) : ResourceMapper<I, O> {
    override fun map(input: Resource<I>): Resource<O> {
        return Resource(
            input.status, map(input.data!!), input.message, input.exception
        )
    }
}