package com.snapp.cache.mappers

interface CacheMapper<T, V> {

    fun mapFromCached(type: T): V
    fun mapToCached(type: V): T
}