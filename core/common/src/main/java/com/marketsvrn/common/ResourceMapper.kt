package com.marketsvrn.common

interface ResourceMapper<I: Any, O: Any> {
    fun map(input: Resource<I>): Resource<O>
}