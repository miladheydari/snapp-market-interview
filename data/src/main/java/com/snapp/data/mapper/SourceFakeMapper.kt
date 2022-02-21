package com.snapp.data.mapper

import com.snapp.data.models.SourceFakeEntity
import com.snapp.domain.models.SourceFake
import javax.inject.Inject

class SourceFakeMapper @Inject constructor() :
    Mapper<SourceFakeEntity, SourceFake> {

    override fun mapFromEntity(type: SourceFakeEntity): SourceFake {
        return SourceFake(type.id,type.name)
    }

    override fun mapToEntity(type: SourceFake): SourceFakeEntity {
        return SourceFakeEntity(type.id, type.name)
    }
}
