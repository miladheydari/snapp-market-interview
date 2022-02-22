package com.snapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.snapp.data.mapper.SourceMapper
import com.snapp.data.repository.SourceCache
import com.snapp.data.repository.SourceRemote
import com.snapp.domain.models.Article
import com.snapp.domain.models.Source
import com.snapp.domain.repository.SourceRepository
import com.snapp.domain.state.ArticleViewState
import com.snapp.domain.state.SourceViewState
import com.snapp.domain.state.ViewState
import com.snapp.domain.utils.Resource
import io.reactivex.Single
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val sourceCache: SourceCache,
    private val sourceRemote: SourceRemote,
    private val sourceMapper: SourceMapper

) : SourceRepository {


    override fun loadSource(fetchRequired: Boolean): LiveData<SourceViewState> {
        val data = object :
            NetworkBoundResource<List<Source>, List<Source>>() {
            override fun saveCallResult(item: List<Source>) {
                sourceCache.insertSources(item.map(sourceMapper::mapToEntity))
            }

            override fun shouldFetch(data: List<Source>?): Boolean {
                return fetchRequired
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
        return convert(data)
    }


    private fun convert(data: LiveData<Resource<List<Source>>>): LiveData<SourceViewState> {
        return data.map {
            when (it) {
                is Resource.Loading -> SourceViewState(ViewState.LOADING)
                is Resource.Error -> SourceViewState(
                    ViewState.ERROR,
                    it.data,
                    it.massage,
                    it.throwable
                )
                is Resource.Success -> SourceViewState(ViewState.SUCCESS, it.data)
            }

        }

    }
}