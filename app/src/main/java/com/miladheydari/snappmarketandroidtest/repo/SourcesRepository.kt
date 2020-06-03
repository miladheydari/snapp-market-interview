package com.miladheydari.snappmarketandroidtest.repo

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.miladheydari.snappmarketandroidtest.core.Constants.NetworkService.RATE_LIMITER_TYPE

import com.miladheydari.snappmarketandroidtest.domain.datasource.SourcesLocalDataSource
import com.miladheydari.snappmarketandroidtest.domain.datasource.SourcesRemoteDataSource
import com.miladheydari.snappmarketandroidtest.domain.model.Source
import com.miladheydari.snappmarketandroidtest.domain.model.SourceList

import com.miladheydari.snappmarketandroidtest.utils.domain.RateLimiter
import com.miladheydari.snappmarketandroidtest.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SourcesRepository @Inject constructor(
    private val sourcesRemoteDataSource: SourcesRemoteDataSource,
    private val sourcesLocalDataSource: SourcesLocalDataSource
) {

    private val rateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadSource(fetchRequired: Boolean): LiveData<Resource<List<Source>>> {
        return object :
            NetworkBoundResource<List<Source>, SourceList>() {
            override fun saveCallResult(item: SourceList) {

                sourcesLocalDataSource.insertSources(item.sources)
            }

            override fun shouldFetch(data: List<Source>?): Boolean {
                return (fetchRequired && data?.size == 0)
            }


            override fun loadFromDb(): LiveData<List<Source>> {
                return sourcesLocalDataSource.getSources()
            }

            override fun createCall(): Single<SourceList> {
                return sourcesRemoteDataSource.getSources()
            }

            override fun onFetchFailed() {
                rateLimit.reset(RATE_LIMITER_TYPE)
            }

        }.asLiveData
    }
}
