package com.snapp.remote.mappers

import com.snapp.data.models.SourceEntity
import com.snapp.remote.models.SourceModel
import javax.inject.Inject

class SourceEntityMapper @Inject constructor() :
    EntityMapper<SourceModel, SourceEntity> {

    override fun mapFromModel(model: SourceModel): SourceEntity {
        return SourceEntity(
            model.id,
            model.name,
            model.description,
            model.url,
            model.category,
            model.language,
            model.country
        )

    }
}
