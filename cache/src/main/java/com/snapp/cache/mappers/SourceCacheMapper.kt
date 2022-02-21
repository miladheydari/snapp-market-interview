package com.snapp.cache.mappers

import com.snapp.cache.models.SourceCacheEntity
import com.snapp.data.models.SourceEntity
import javax.inject.Inject


class SourceCacheMapper @Inject constructor() :
    CacheMapper<SourceCacheEntity, SourceEntity> {


    override fun mapFromCached(type: SourceCacheEntity): SourceEntity {
        return SourceEntity(
            type.id,
            type.name,
            type.description,
            type.url,
            type.category,
            type.language,
            type.country
        )

    }

    override fun mapToCached(type: SourceEntity): SourceCacheEntity {
        return SourceCacheEntity(
            type.id,
            type.name,
            type.description,
            type.url,
            type.category,
            type.language,
            type.country
        )

    }
}
