package com.snapp.cache


import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.snapp.cache.dao.SourceDao
import com.snapp.cache.mappers.SourceCacheMapper
import com.snapp.data.models.SourceEntity
import com.snapp.data.repository.SourceCache
import javax.inject.Inject

class SourceLocalDataSource @Inject constructor(
    private val sourceDao: SourceDao,
    private val sourceCacheMapper: SourceCacheMapper
) : SourceCache {
    override fun getSources(): LiveData<List<SourceEntity>> {

        return sourceDao.getSources().map {
            it.map(sourceCacheMapper::mapFromCached)
        }
    }

    override fun insertSources(sources: List<SourceEntity>) {

        sourceDao.insertReplaceSources(sources.map(sourceCacheMapper::mapToCached))
    }


}
