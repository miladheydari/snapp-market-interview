package com.snapp.remote.mappers

import com.snapp.data.models.SourceFakeEntity
import com.snapp.remote.models.SourceFakeModel
import javax.inject.Inject

class SourceFakeEntityMapper @Inject constructor() :
    EntityMapper<SourceFakeModel, SourceFakeEntity> {

    override fun mapFromModel(model: SourceFakeModel): SourceFakeEntity {
        return  SourceFakeEntity(model.id,model.name)
    }
}
