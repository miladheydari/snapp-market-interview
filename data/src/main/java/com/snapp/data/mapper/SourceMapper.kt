package com.snapp.data.mapper

import com.snapp.data.models.SourceEntity
import com.snapp.domain.models.Source
import javax.inject.Inject

class SourceMapper @Inject constructor() :
    Mapper<SourceEntity, Source> {

    override fun mapFromEntity(type: SourceEntity): Source {
        return Source(
            type.id,
            type.name,
            type.description,
            type.url,
            type.category,
            type.language,
            type.country
        )
    }

    override fun mapToEntity(type: Source): SourceEntity {
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
}
