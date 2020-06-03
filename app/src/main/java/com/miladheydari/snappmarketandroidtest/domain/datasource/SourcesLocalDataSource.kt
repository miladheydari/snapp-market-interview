package com.miladheydari.snappmarketandroidtest.domain.datasource

import com.miladheydari.snappmarketandroidtest.db.dao.SourcesDao

import com.miladheydari.snappmarketandroidtest.domain.model.Source

import javax.inject.Inject

class SourcesLocalDataSource @Inject constructor(private val sourcesDao: SourcesDao) {

    fun getSources() = sourcesDao.getSources()

   
    fun insertSources(sources: List<Source>) = sourcesDao.insertReplaceSources(sources)

}
