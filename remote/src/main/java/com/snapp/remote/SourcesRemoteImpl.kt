package com.snapp.remote


import com.snapp.data.models.SourceEntity
import com.snapp.data.repository.SourceRemote
import com.snapp.remote.api.TestAppAPI
import com.snapp.remote.mappers.SourceEntityMapper
import io.reactivex.Single
import javax.inject.Inject

class SourcesRemoteImpl @Inject constructor(
    private val api: TestAppAPI,
    private val sourceEntityMapper: SourceEntityMapper
) : SourceRemote {
    override fun getSources(): Single<List<SourceEntity>> {

        return api.getSources().map {
            it.sources.map(sourceEntityMapper::mapFromModel)
        }

    }


}
