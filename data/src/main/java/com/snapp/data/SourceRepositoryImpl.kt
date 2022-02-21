package com.snapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.snapp.data.mapper.SourceMapper
import com.snapp.data.repository.SourceCache
import com.snapp.data.repository.SourceRemote
import com.snapp.domain.models.Source
import com.snapp.domain.repository.SourceRepository
import com.snapp.domain.state.SourceViewState
import io.reactivex.Single
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val sourceCache: SourceCache,
    private val sourceRemote: SourceRemote,
    private val sourceMapper: SourceMapper

):SourceRepository {


    override fun loadSource(fetchRequired: Boolean): LiveData<SourceViewState> {
        return object :
            NetworkBoundResource<List<Source>, List<Source>,SourceViewState>() {
            override fun saveCallResult(item: List<Source>) {
                sourceCache.insertSources(item.map(sourceMapper::mapToEntity))
            }

            override fun shouldFetch(data: List<Source>?): Boolean {
                return (fetchRequired && data?.size == 0)
            }


            override fun loadFromDb(): LiveData<List<Source>> {
                return sourceCache.getSources().map {
                    it.map(sourceMapper::mapFromEntity)
                }
            }

            override fun createCall(): Single<List<Source>> {
                return sourceRemote.getSources().map { it.map(sourceMapper::mapFromEntity) }
            }


        }.asLiveData
    }
}
