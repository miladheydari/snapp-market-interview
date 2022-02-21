package com.snapp.cache.mappers

import com.snapp.cache.models.SourceFakeCacheEntity
import com.snapp.data.models.SourceFakeEntity
import javax.inject.Inject

class SourceFakeCacheMapper @Inject constructor() :
    CacheMapper<SourceFakeCacheEntity, SourceFakeEntity> {
    override fun mapFromCached(type: SourceFakeCacheEntity): SourceFakeEntity {
        return  SourceFakeEntity(type.id,type.name)
    }

    override fun mapToCached(type: SourceFakeEntity): SourceFakeCacheEntity {
        return  SourceFakeCacheEntity(type.id,type.name)
    }

}
